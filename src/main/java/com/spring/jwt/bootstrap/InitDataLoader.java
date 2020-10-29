package com.spring.jwt.bootstrap;


import com.spring.jwt.exception.provider.ProviderNotFoundException;
import com.spring.jwt.exception.role.RoleNotFoundException;
import com.spring.jwt.model.Provider;
import com.spring.jwt.model.Role;
import com.spring.jwt.model.User;
import com.spring.jwt.model.enumerated.RoleName;
import com.spring.jwt.model.ids.UserId;
import com.spring.jwt.repository.ProviderRepository;
import com.spring.jwt.repository.RoleRepository;
import com.spring.jwt.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InitDataLoader implements CommandLineRunner {

   final RoleRepository roleRepository;

   final UserRepository userRepository;

   final ProviderRepository providerRepository;

   final PasswordEncoder passwordEncoder;


    @Value("${admin.username}")
    String adminUsername;

    @Value("${admin.password}")
    String adminPassword;


    @Override
    public void run(String... args) throws Exception {
        if(providerRepository.findAll().isEmpty()){
            initProvider();

        }

        if(roleRepository.findAll().isEmpty()){
            initRole();
        }

        if(userRepository.findAll().isEmpty()){
            initAdminUser();

        }
    }

    private void initProvider(){
        Provider providerLocal=new Provider();
        providerLocal.setName("local");

        Provider providerCompany=new Provider();
        providerCompany.setName("company");

        providerRepository.save(providerLocal);
        providerRepository.save(providerCompany);
    }

    private void initRole(){
        Role roleAdmin=new Role(RoleName.ROLE_ADMIN);
        Role roleUser=new Role(RoleName.ROLE_USER);

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

    }

    private void initAdminUser(){

        Provider provider = providerRepository.findByName("local")
                .orElseThrow(() -> new ProviderNotFoundException());

        User user = new User();
        user.setId(new UserId("admin",provider.getId()));
        user.setProvider(provider);

        user.setUsername(adminUsername);
        user.setEmail("meet.admin@gmail.com");
        user.setPassword(passwordEncoder.encode(adminPassword));

        Role role = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RoleNotFoundException());

        user.setRole(role);

        userRepository.save(user);
    }
}
