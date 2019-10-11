package com.lambdaschool.zoos;

// Adapted from work by Vivek Vishwanath

import com.lambdaschool.zoos.model.Role;
import com.lambdaschool.zoos.model.User;
import com.lambdaschool.zoos.model.UserRoles;
import com.lambdaschool.zoos.model.Useremail;
import com.lambdaschool.zoos.repository.RoleRepository;
import com.lambdaschool.zoos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{

    RoleRepository rolerepos;
    UserRepository userrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("ADMIN");
        Role r2 = new Role("ZOODATA");
        Role r3 = new Role("ANIMALDATA");
        Role r4 = new Role("MGR");

        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);
        rolerepos.save(r4);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        User u1 = new User("admin", "password", "admin@home.local", admins);
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@mymail.local"));
        userrepos.save(u1);

        ArrayList<UserRoles> zoodata = new ArrayList<>();
        zoodata.add(new UserRoles(new User(), r2));
        User u2 = new User("barnbarn", "ILuvM4th!", "barnbarn@home.local", zoodata);
        u2.getUseremails()
          .add(new Useremail(u2,
                             "barnbarn@email.local"));
        userrepos.save(u2);

        ArrayList<UserRoles> animaldata = new ArrayList<>();
        animaldata.add(new UserRoles(new User(), r3));
        User u3 = new User("cinnamon", "password", "cinnamon@home.local", animaldata);
        u3.getUseremails()
          .add(new Useremail(u3,
                             "cinnamon@mymail.local"));
        u3.getUseremails()
          .add(new Useremail(u3,
                             "hops@mymail.local"));
        u3.getUseremails()
          .add(new Useremail(u3,
                             "bunny@email.local"));
        userrepos.save(u3);

        ArrayList<UserRoles> mgrdata = new ArrayList<>();
        mgrdata.add(new UserRoles(new User(), r4));
        User u4 = new User("tiger", "password", "tiger@home.local", mgrdata);
        userrepos.save(u4);

        admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        User u5 = new User("hops", "password", "hops@home.local", admins);
        userrepos.save(u5);
    }
}