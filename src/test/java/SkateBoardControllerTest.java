import com.detroitlabs.skateboard.NewMobAssignmentsApplication;
import com.detroitlabs.skateboard.controller.SkateBoardController;
import com.detroitlabs.skateboard.model.SkateBoard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.util.ArrayList;

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NewMobAssignmentsApplication.class)
public class SkateBoardControllerTest {

    ArrayList<SkateBoard> skateBoards = new ArrayList<>();

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private SkateBoardController skateBoardController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        skateBoards.add(SkateBoard.builder()
                .id(1)
                .ownerName("Rohan Dongre")
                .brand("Adidas")
                .weight(60)
                .length(20)
                .isBoardAvailable(true)
                .build());
        skateBoards.add(SkateBoard.builder()
                .id(2)
                .ownerName("John Hive")
                .brand("Nike")
                .weight(80)
                .length(23)
                .isBoardAvailable(true)
                .build());
        skateBoards.add(SkateBoard.builder()
                .id(3)
                .ownerName("Brittany Spear")
                .brand("Puma")
                .weight(65)
                .length(30)
                .isBoardAvailable(true)
                .build());

        mockMvc = MockMvcBuilders.standaloneSetup(this.skateBoardController).build();
    }

    @Test
    public void createSkateBoard_returnsCreatedSuccessfullyCode_WhenIdIsSet() throws Exception {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/skateboard/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        SkateBoard createSkateBoard = SkateBoard.builder()
                .id(4)
                .ownerName("Rohan Dongre")
                .brand("Adidas")
                .weight(60)
                .length(20)
                .isBoardAvailable(true)
                .build();

        HttpEntity<SkateBoard> request = new HttpEntity<>(createSkateBoard, headers);
        ResponseEntity actualResponse = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(201, actualResponse.getStatusCodeValue());
    }

    @Test
    public void getSkateBoard_ReturnsSuccessfulCode_IfFound() throws Exception {
        SkateBoard skateBoard1 = skateBoardController.retrieveSkateBoard(1);
        Assert.assertEquals(1, skateBoard1.getId().intValue());

        SkateBoard skateBoard2 = skateBoardController.retrieveSkateBoard(2);
        Assert.assertEquals(2, skateBoard2.getId().intValue());

        //when value not present
        SkateBoard skateBoard3 = skateBoardController.retrieveSkateBoard(3);
        Assert.assertNotEquals(1, skateBoard3.getId().intValue());
    }

    @After
    public void tearDown() {
        for (SkateBoard skateBoard : skateBoards) {
            skateBoards.remove(skateBoard.getId());
        }
    }

}
