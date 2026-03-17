package com.example.blog_management.Service;

import com.example.blog_management.DTO.ForgotPasswordDto;
import com.example.blog_management.DTO.userDTO;
import com.example.blog_management.Model.PasswordResetToken;
import com.example.blog_management.Model.User;
import com.example.blog_management.Repo.PasswordResetTokenrepo;
import com.example.blog_management.Repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordResetTokenrepo passwordResetTokenrepo;
    @Transactional
    public String sendOtp(ForgotPasswordDto forgotPasswordDto) {
        User user = userRepo.findByEmail(forgotPasswordDto.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        passwordResetTokenrepo.deleteByEmail(forgotPasswordDto.getEmail());

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(forgotPasswordDto.getEmail());
        token.setOtp(otp);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        token.setVerified(false);

        passwordResetTokenrepo.save(token);

        emailService.sendOTP(forgotPasswordDto.getEmail(),otp);

        return "OTP sent successfully";
    }
    @Transactional
    public String verifyOtp(String email, String otp) {

        PasswordResetToken token = passwordResetTokenrepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (!token.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        token.setVerified(true);
        passwordResetTokenrepo.save(token);

        return "OTP verified successfully";
    }
    @Transactional
    public String resetPassword(String email, String newPassword) {

        PasswordResetToken token = passwordResetTokenrepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (!token.isVerified()) {
            throw new RuntimeException("OTP not verified");
        }

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(newPassword);
        userRepo.save(user);

        passwordResetTokenrepo.deleteByEmail(email);

        return "Password reset successfully";
    }

    public ResponseEntity<userDTO> getuserbyid(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User user = userRepo.getReferenceById(id);
        userDTO userDTO = new userDTO(user.getId(),user.getUsername(),user.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    public User saveUser(User user) {
        emailService.sendEmail(user.getEmail(),"Registered in Blog press","you are registered in blog press for experince our seemless services login at blogpress ");
        return userRepo.save(user);
    }

    public void deleteuser(Integer id) {
        userRepo.deleteById(id);
    }

    public ResponseEntity<userDTO> getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        userDTO userDTO = new userDTO(user.getId(), user.getUsername(),user.getEmail());
        return ResponseEntity.ok(userDTO);
    }
}
