package com.gy.cloud.security.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class RoleBasedVoter implements AccessDecisionVoter<Object> {

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class clazz) {
    return true;
  }

  @Override
  public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

    log.info(">> vote time!");

    if (true)
      return ACCESS_GRANTED;

    // 没有认证
    if(authentication == null) {
      return ACCESS_DENIED;
    }

    int result = ACCESS_ABSTAIN;
    Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

    for (ConfigAttribute attribute : attributes) {

      if(attribute.getAttribute()==null)
        continue;

      if (this.supports(attribute)) {
        result = ACCESS_DENIED;
        // Attempt to find a matching granted authority
        for (GrantedAuthority authority : authorities)
          if (attribute.getAttribute().equals(authority.getAuthority()))
            return ACCESS_GRANTED;
      }
    }

    return result;
  }

  private Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {

    return authentication.getAuthorities();
  }
}