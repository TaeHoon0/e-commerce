package kr.hhplus.be.server.user.presentation.dto.response;

import kr.hhplus.be.server.user.domain.UserType;


public record UserResponseDto (

     Long id,
     String userId,
     String name,
     UserType type

){}
