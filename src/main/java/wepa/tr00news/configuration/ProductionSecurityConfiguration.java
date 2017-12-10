package wepa.tr00news.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("production")
@Configuration
@EnableWebSecurity
public class ProductionSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*  Restrict /admin and all its subfolders only to authenticated users,
            permit everything else to everyone. Maybe not kosher to do this
            the "wrong way around", but the list of addresses to permit is much
            longer than those to restrict. So, simpler this way.
        */
        http.authorizeRequests()
                .antMatchers(
                        "/admin",
                        "/admin/**"
                ).authenticated()
                .anyRequest().permitAll();

        http.formLogin()
                .permitAll().and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(System.getenv().get("TR00NEWS_PASSWORD")) // heroku config var
                .authorities("ADMIN");
    }

}