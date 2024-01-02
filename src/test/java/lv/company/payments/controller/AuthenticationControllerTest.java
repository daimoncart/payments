package lv.company.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.company.payments.model.AuthRequest;
import lv.company.payments.security.JwtIssuer;
import lv.company.payments.security.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JwtIssuer jwtIssuer;

    @Mock
    private JwtProperties jwtProperties;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(authenticationController).build();
    }

    @Test
    public void testSuccessfulLoginUser() throws Exception {
        AuthRequest request = new AuthRequest("login_user@inbox.lv", "password1234");
        String token = "generatedToken";

        when(jwtIssuer.issue(anyLong(), anyString(), anyList())).thenReturn(token);
        when(jwtProperties.getExpiryMinutes()).thenReturn(30L);

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(jwtIssuer, times(1)).issue(anyLong(), anyString(), anyList());
        verify(jwtProperties, times(1)).getExpiryMinutes();
    }

    @Test
    public void testThrowingExceptionWhenDetailsAreInvalid() throws Exception {
        AuthRequest request = new AuthRequest("login_user", "password1234");

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
