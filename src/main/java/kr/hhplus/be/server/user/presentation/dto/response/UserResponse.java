package kr.hhplus.be.server.user.presentation.dto.response;

import kr.hhplus.be.server.user.domain.UserType;


public record UserResponse(

     Long id,
     String email,
     String name,
     UserType type

){}
