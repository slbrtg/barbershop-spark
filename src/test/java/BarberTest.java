import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BarberTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void barber_instantiatesCorrectly_true(){
    Barber testBarber = new Barber("red");
    assertTrue(testBarber instanceof Barber);
  }

  @Test
  public void getName_returnsBarberName_red(){
    Barber testBarber = new Barber("red");
    assertEquals("red", testBarber.getName());
  }

  @Test
  public void save_createsIdForBarberInDatabase_true(){
    Barber testBarber = new Barber("red");
    testBarber.save();
    assertTrue(testBarber.getId() > 0);
  }
}
