package com.orbit.portailAgentStockPR.utilisateur.controller;


import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.utilisateur.models.AuthenticationRequest;
import com.orbit.portailAgentStockPR.utilisateur.models.AuthenticationResponse;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import com.orbit.portailAgentStockPR.utilisateur.service.MyUserDetailsService;
import com.orbit.portailAgentStockPR.utilisateur.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
public class LoginController {


    private AuthenticationManager authenticationManager ;

    private MyUserDetailsService userDetailService;

    private JwtUtil jwtTokenUtil ;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
    {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
            ) ;
        }
        catch(BadCredentialsException e) {
            throw new ApiRequestException("mot de passe incorrect",e);
        }
        catch (Exception e)
        {
            throw new ApiRequestException("exception inconnu "+e.getMessage());
        }
        final MyUserDetails userDetails = (MyUserDetails) userDetailService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
