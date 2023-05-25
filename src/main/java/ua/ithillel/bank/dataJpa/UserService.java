package ua.ithillel.bank.dataJpa;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getById(Long id){
        User user = userRepository.getReferenceById(id);
        System.out.println("user #" + id + " " + user.toString());
        return user;
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void update(User updatedUser){
        updatedUser.setId(updatedUser.getId());  // чтобы метод save не создавал новую запись
        userRepository.save(updatedUser);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
