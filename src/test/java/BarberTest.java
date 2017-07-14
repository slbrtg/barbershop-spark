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
}
