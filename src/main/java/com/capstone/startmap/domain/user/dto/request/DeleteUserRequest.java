package com.capstone.startmap.domain.user.dto.request;

import com.capstone.startmap.util.validation.EmailGroup;
import com.capstone.startmap.util.validation.PasswordGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = "회원 탈퇴 요청 DTO")
public class DeleteUserRequest {

    @Schema(description = "비밀번호 | 자체 회원가입일 경우: 비밀번호 그대로 입력 | 카카오회원일 경우: 고정값 입력")
    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.", groups = PasswordGroup.class)
    @Size(min = 8, max = 16, groups = PasswordGroup.class)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$",
            message = "비밀번호는 영소문자와 숫자, 특수기호를 1개이상 포함시켜 8자 이상 입력해주세요",
            groups = PasswordGroup.class)
    private String password;

    @Schema(description = "자체 회원가입일 경우: 회원 이메일 입력 | 카카오회원일 경우: 최초에 받은 kakaoid 입력")
    @NotBlank(message = "이메일은 필수 입력 사항입니다.", groups = EmailGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$",
            message = "이메일 형식을 지켜주세요.",
            groups = EmailGroup.class)
    @Size(max = 30, groups = EmailGroup.class)
    private String kakao_id; //카카오 id는 이메일 형식임

}
