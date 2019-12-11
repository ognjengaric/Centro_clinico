package hermanos.Centro.Clinico.service.interfaces;

import hermanos.Centro.Clinico.model.Authority;

import java.util.List;

public interface AuthorityServiceInterface {
    List<Authority> findById(Long id);
    List<Authority> findByName(String name);
}
