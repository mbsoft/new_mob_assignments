import com.detroitlabs.skateboard.NewMobAssignmentsApplication;
import com.detroitlabs.skateboard.controller.SkateBoardResource;
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
import java.util.List;

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NewMobAssignmentsApplication.class)
public class SkateBoardTest {

    ArrayList<SkateBoard> skateBoards = new ArrayList<>();

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private SkateBoardResource skateBoardResource;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        skateBoards.add(SkateBoard.builder()
                .id(1)
                .ownerName("Rohan Dongre")
                .brand("Adidas")
                .weight(40)
                .length(20)
                .isBoardAvailable(true)
                .build());
        skateBoards.add(SkateBoard.builder()
                .id(2)
                .ownerName("John Hive")
                .brand("Nike")
                .weight(50)
                .length(22)
                .isBoardAvailable(true)
                .build());
        skateBoards.add(SkateBoard.builder()
                .id(3)
                .ownerName("Brittany Spear")
                .brand("Puma")
                .weight(48)
                .length(24)
                .isBoardAvailable(true)
                .build());

        mockMvc = MockMvcBuilders.standaloneSetup(this.skateBoardResource).build();
    }

    @Test
    public void createSkateBoard_returnsCreatedSuccessfullyCode_WhenIdIsSet() throws Exception {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/createSkateboard/";
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
    public void getSkateBoardById_ReturnsSuccessful_IfFound() throws Exception {
        SkateBoard skateBoard1 = skateBoardResource.retrieveSkateBoard(1);
        Assert.assertEquals(1, skateBoard1.getId().intValue());

        SkateBoard skateBoard2 = skateBoardResource.retrieveSkateBoard(2);
        Assert.assertEquals(2, skateBoard2.getId().intValue());

        //when value not present
        SkateBoard skateBoard3 = skateBoardResource.retrieveSkateBoard(3);
        Assert.assertNotEquals(1, skateBoard3.getId().intValue());
    }

    @Test
    public void getAllAvailableBoards_ReturnsSuccessful_IfFound() throws Exception {
        List<SkateBoard> boards = skateBoardResource.retrieveAllSkateboards();
        Assert.assertEquals(3, boards.size());
    }

    @Test
    public void getSkateBoardByLength_ReturnsSuccessful_IfFound() throws Exception {
        SkateBoard skateBoard1 = skateBoardResource.retrieveSkateBoardByLength(20);
        Assert.assertEquals(20, skateBoard1.getLength());

        SkateBoard skateBoard2 = skateBoardResource.retrieveSkateBoardByLength(22);
        Assert.assertEquals(22, skateBoard2.getLength());

        SkateBoard skateBoard3 = skateBoardResource.retrieveSkateBoardByLength(24);
        Assert.assertNotEquals(21, skateBoard3.getLength());
    }

    @Test
    public void getSkateBoardByWeight_ReturnsSuccessful_IfFound() throws Exception {
        SkateBoard skateBoard1 = skateBoardResource.retrieveSkateBoardByWeight(40);
        Assert.assertEquals(40, skateBoard1.getWeight());

        SkateBoard skateBoard2 = skateBoardResource.retrieveSkateBoardByWeight(50);
        Assert.assertEquals(50, skateBoard2.getWeight());

        SkateBoard skateBoard3 = skateBoardResource.retrieveSkateBoardByWeight(48);
        Assert.assertNotEquals(22, skateBoard3.getWeight());
    }

    @Test
    public void getSkateBoardByBrand_ReturnsSuccessful_IfFound() throws Exception {
        SkateBoard skateBoard1 = skateBoardResource.retrieveSkateBoardByBrand("Nike");
        Assert.assertEquals("Nike", skateBoard1.getBrand());

        SkateBoard skateBoard2 = skateBoardResource.retrieveSkateBoardByBrand("Adidas");
        Assert.assertEquals("Adidas", skateBoard2.getBrand());

        SkateBoard skateBoard3 = skateBoardResource.retrieveSkateBoardByBrand("Puma");
        Assert.assertNotEquals("Rebook", skateBoard3.getBrand());
    }

    @Test
    public void deleteSkateBoardById_ReturnsSuccessful_IfFound() throws Exception {
        ResponseEntity<Object> skateBoard1 = skateBoardResource.deleteSkateBoard(1);
        Assert.assertEquals(200, skateBoard1.getStatusCodeValue());
    }

    @After
    public void tearDown() {
        skateBoards.clear();
    }

}
