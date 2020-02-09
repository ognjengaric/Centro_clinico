package hermanos.Centro.Clinico.repositories;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.StartEndTime;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application.properties")
public class CheckupRepositoryTestsST2 {

        @Autowired
        CheckupRepository checkupRepository;


        @Test
        public void findById(){
            Checkup checkup = checkupRepository.findById(-2);
            assertEquals(checkup.isApproved(), false);
        }

//        @Test
//        public void save(){
//            Checkup c = new Checkup();
//            c.setId(123123123);
//            c.setStartEnd(new StartEndTime(LocalTime.of(7,30), LocalTime.of(8,30)));
//            checkupRepository.save(c);
//            assertNotNull(checkupRepository.findById(123123123));
//        }
}
