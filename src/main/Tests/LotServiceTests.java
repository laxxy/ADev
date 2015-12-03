import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Lot;
import com.audev.common.Service.LotService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by cosxt on 01.12.2015.
 */

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class LotServiceTests {

    @Resource
    private LotService lotService;

    @Before
    public void setup() {

    }

    @Test
    public void tetstcc() {

        /*Lot lot = new Lot();
        Date date = new Date();
        lot.setLotName("asd");
        lot.setDateOfStart(date);
        date.setTime(System.currentTimeMillis() + 30000000);
        lot.setDateOfEnd(date);
        lot.setBidInitial(20.00);
        lot.setLotInfo("asdasdasd");
        lotService.addOne(lot);*/
    }
}
