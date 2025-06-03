package kr.hhplus.be.server.user.application.port.out;

public interface PasswordEncoderPort {

    String encode(String password);

    boolean matches(String password, String encodedPassword);
}
