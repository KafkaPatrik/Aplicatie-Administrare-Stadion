package tests.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.controllers.LoginController;
import org.loose.fis.sre.exceptions.TicketAlreadyExistsException;
import org.loose.fis.sre.model.Ticket;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TicketService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketServiceTest {

    public static final String TICKET = "TicketTest";
    public static final User USER = new User(TICKET,TICKET,"Administrator");;

    @BeforeAll
    static void beforeAll() throws IOException {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        FileUtils.cleanDirectory(applicationHomePath.toFile());
        UserService.initDatabase();
        EventService.initDatabase();
        TicketService.initTicketsDatabase();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Before Each");
        LoginController.current_user = USER;
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each");
    }


    @Test
    @Order(1)
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(TicketService.getAllTickets()).isNotNull();
        assertThat(TicketService.getAllTickets()).isEmpty();
    }

    @Test
    @Order(2)
    void testTicketIsAddedToDatabase() throws TicketAlreadyExistsException {
        TicketService.addTicket(TICKET,TICKET,TICKET,TICKET,100.0, true,TICKET,100);
        assertThat(TicketService.getAllTickets()).isNotEmpty();
        assertThat(TicketService.getAllTickets()).size().isEqualTo(1);
        Ticket ticket = TicketService.getAllTickets().get(0);
        assertThat(ticket).isNotNull();
        assertThat(ticket.getTicketOwnerName()).isEqualTo(TICKET);
        assertThat(ticket.getIdCode().equals(TICKET));
        assertThat(ticket.getPrice()==100);
        assertThat(ticket.getCategory().equals(TICKET));
        assertThat(ticket.getPhoneNumber().equals(TICKET));
        assertThat(ticket.getParkingSpot().equals(TICKET));
        assertThat(ticket.isHasParkingSpot()==true);
        assertThat(ticket.getId_event()==100);
    }


    @Test
    @Order(3)
    void testTicketCanNotBeAddedTwice() {
        assertThrows(TicketAlreadyExistsException.class, () -> {
            TicketService.addTicket(TICKET,TICKET,TICKET,TICKET,100.0, true,TICKET,100);
            TicketService.addTicket(TICKET,TICKET,TICKET,TICKET,100.0, true,TICKET,100);
        });
    }

    @Test
    @Order(4)
    void testReturnTicket() {
        assertThat(TicketService.returnTicket(TICKET)!=null);
    }

    @Test
    @Order(5)
    void testChangeTicketCategory() {
        Ticket ticket = TicketService.getAllTickets().get(0);
        ticket.changeCategory("Cat A");
        assertThat(ticket.getCategory().equals("Cat A"));
    }
}
