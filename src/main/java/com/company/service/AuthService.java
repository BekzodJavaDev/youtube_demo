package com.company.service;


import com.company.config.springSicurityConfig.CustomUserDetails;
import com.company.dto.auth.AuthDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.dto.profile.RegistrationDTO;
import com.company.dto.response.ResponseInfoDTO;
import com.company.entity.EmailHistoryEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.EmailHistoryRepository;
import com.company.repository.ProfileRepository;
import com.company.util.springSicurityUtil.BCryptUtil;
import com.company.util.springSicurityUtil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    public ProfileDTO login(AuthDTO authDTO) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        CustomUserDetails user = (CustomUserDetails) authenticate.getPrincipal();
        ProfileEntity profile = user.getProfile();

        ProfileDTO dto = new ProfileDTO();
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setJwt(JwtUtil.encode(profile.getId()));
        return dto;
    }

    public String registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
       entity.setPassword(dto.getPassword());
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setRole(ProfileRole.ROLE_USER);
        profileRepository.save(entity);


        emailService.sendRegistrationEmail(entity.getEmail(), entity.getId());

        return "Activation code was sent to "+dto.getEmail();
    }


    public String emailVerification(Integer id) {
        Optional<ProfileEntity> profile = profileRepository.findById(id);
        if (profile.isEmpty()) {
            return "<h1>User Not Found</h1>";
        }

        ProfileEntity profileEntity = profile.get();
        Optional<EmailHistoryEntity> emailHistory = emailHistoryRepository.findTopByEmailOrderByCreatedDateDesc(profileEntity.getEmail());
        if (emailHistory.isEmpty()) {
            return "Email Not Found";
        }

        EmailHistoryEntity email = emailHistory.get();
        LocalDateTime validDate = email.getCreatedDate().plusMinutes(1);

        if (validDate.isBefore(LocalDateTime.now())) {
            return "<h1 style='align-text:center'>Time is out.</h1>";
        }

        profileEntity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(profileEntity);
        return "<h1 style='align-text:center'>Success. Tabriklaymiz.</h1>";
    }
    public ResponseInfoDTO resendEmail(String email) {

        Optional<ProfileEntity> byEmail = profileRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new ItemNotFoundException("User not found");
        }

        ProfileEntity entity = byEmail.get();
        if(entity.getStatus().equals(ProfileStatus.ACTIVE)){
            throw new BadRequestException("User has already verified by email");
        }
        Long count = emailService.getCountByEmail(email);
        if(count>=3){
            throw new BadRequestException("Too many attempts. Try later");
        }
        emailService.sendRegistrationEmail( email,entity.getId());
        return new ResponseInfoDTO(1,"Link was sent to " +email);
    }


}
