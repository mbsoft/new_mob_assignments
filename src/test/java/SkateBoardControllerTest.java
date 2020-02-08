import com.detroitlabs.skateboard.NewMobAssignmentsApplication;
import com.detroitlabs.skateboard.model.SkateBoard;
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

import java.net.URI;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NewMobAssignmentsApplication.class)
public class SkateBoardControllerTest {

    ArrayList<SkateBoard> skateBoards = new ArrayList<>();

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;


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


}
