package hermanos.Centro.Clinico;

import hermanos.Centro.Clinico.model.Checkup;
import hermanos.Centro.Clinico.model.PredefinedCheckup;
import hermanos.Centro.Clinico.model.Room;
import hermanos.Centro.Clinico.repository.CheckupRepository;
import hermanos.Centro.Clinico.repository.PredefinedCheckupRepository;
import hermanos.Centro.Clinico.repository.RoomRepository;
import hermanos.Centro.Clinico.service.CheckupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
 import static org.mockito.Mockito.times;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.verifyNoMoreInteractions;
 import static org.mockito.Mockito.when;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
 import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static hermanos.Centro.Clinico.constants.PersonConstant.DB_R_ID;
import static hermanos.Centro.Clinico.constants.PersonConstant.DB_PC_ID;
import static hermanos.Centro.Clinico.constants.PersonConstant.DB_CH_BR;
import static hermanos.Centro.Clinico.constants.PersonConstant.DB_CH_ID;

/**
 * @author Dimitrije Stojanov ra73/2016
 **/

@RunWith(SpringRunner.class)
@DataJpaTest
public class Student3RepoTest {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PredefinedCheckupRepository predefinedCheckupRepository;
    @Autowired
    private CheckupRepository checkupRepository;



    //RoomRepository.findById
    @Test
    public void testFindByIdRoom() throws Exception{

        Room soba = this.roomRepository.findById(DB_R_ID);
        assertThat(soba.getId()).isEqualTo(DB_R_ID);
    }

    //PredefinedCheckupService.findById
    @Test
    public void testFindByIdPredefinedCheckup() throws Exception{

        PredefinedCheckup pc = this.predefinedCheckupRepository.findById(DB_PC_ID);
        assertThat(pc.getId()).isEqualTo(DB_PC_ID);
    }

    //CheckupService.findAll();
    @Test
    public void testFindByAllCheckupService() throws Exception{

        List<Checkup> pc = this.checkupRepository.findAll();
        assertThat(pc.size()).isEqualTo(DB_CH_BR);
    }
    //CheckupService.deleteById();
/*    @Test
    public void testDeleteByIdCheckup() throws Exception{

        Checkup ch = this.checkupRepository.findById(DB_CH_ID);
        this.checkupRepository.delete(ch);
        //List<Checkup> pc = this.checkupRepository.findAll();
        assertThat(this.checkupRepository.findAll().size()).isEqualTo(DB_CH_BR - 1);
    }*/
}
