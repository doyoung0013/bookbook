package com.example.bookbook.domain.dto;



import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.bookbook.domain.entity.Role;
import com.example.bookbook.domain.entity.SellerEntity;
import com.example.bookbook.domain.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CombinedSellerDTO {
	// UserSaveDTO fields
    private String userName;
    private String userRRN;
    private String gender;
    private String email;
    private String phoneNumber;
    private String password;
    private String birthDate;
    private String postcode;
    private String address;
    private String extraAddress;
    private String detailAddress;

    // SellerSaveDTO fields
    private String shopName;
    private String telNum;
    private String businessNum;
    private String businessReg;
    private String bank;
    private String account;
    private String accountHolder;
    private String settlementAmount;
    private String businessRegCopy;
    private Long businessRegImageId;

    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .userName(userName)
                .userRRN(userRRN)
                .gender(gender)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .birthDate(birthDate)
                .postcode(postcode)
                .address(address)
                .extraAddress(extraAddress)
                .detailAddress(detailAddress)
                .build();
    }

    public SellerEntity toSellerEntity() {
        return SellerEntity.builder()
                .shopName(shopName)
                .telNum(telNum)
                .businessNum(businessNum)
                .businessReg(businessReg)
                .bank(bank)
                .account(account)
                .accountHolder(accountHolder)
                .settlementAmount(settlementAmount)
                .businessRegCopy(businessRegCopy)
                
                // User fields
                .userName(userName)
                .userRRN(userRRN)
                .gender(gender)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .birthDate(birthDate)
                .postcode(postcode)
                .address(address)
                .extraAddress(extraAddress)
                .detailAddress(detailAddress)
                .build();
    }
}