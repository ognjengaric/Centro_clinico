package hermanos.Centro.Clinico.repositories;

import hermanos.Centro.Clinico.model.Clinic;
import hermanos.Centro.Clinico.repository.ClinicRepository;
import hermanos.Centro.Clinico.service.ClinicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application.properties")
public class ClinicRepositoryTests {
        public static final long CLINIC_ID_1 = 0L;
        public static final String CLINIC_NAME = "Medicino de clinico";

        @Autowired
        ClinicRepository clinicRepository;


        @Test
        public void findById(){
            Clinic clinic = clinicRepository.findById(CLINIC_ID_1);
            assertEquals(clinic.getName(), "Medicino de clinico");
        }

        @Test
        public void findAll(){
            List<Clinic> clinics = clinicRepository.findAll();
            assertEquals(clinics.size(), 16);
        }
//        @Test
//        public void save(){
//            Clinic c = new Clinic();
//            c.setName("TESTNAMECLINIC");
//            c.setAddress(null);
//            c.setId(10101010);
//            clinicRepository.save(c);
//            assertNotNull(clinicRepository.findById(10101010));
//        }
}
