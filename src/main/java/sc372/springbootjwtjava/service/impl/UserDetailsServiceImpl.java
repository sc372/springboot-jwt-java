package sc372.springbootjwtjava.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sc372.springbootjwtjava.domain.Role;
import sc372.springbootjwtjava.domain.User;
import sc372.springbootjwtjava.repository.UserRepository;
// import lombok.AllArgsConstructor;

@Service(value = "userDetailsService")
// @AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(userEmail);
        if (user == null) throw new UsernameNotFoundException("이메일 혹은 패스워드가 일치하지 않습니다.");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserEmail()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),
                grantedAuthorities);
    }
}