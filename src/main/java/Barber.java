import org.sql2o.*;
import java.util.List;

public class Barber{
  private int id;
  private String name;

  public Barber (String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object otherBarber){
    if(!(otherBarber instanceof Barber)){
      return false;
    } else {
      Barber newBarber = (Barber) otherBarber;
      return this.getId() == newBarber.getId() &&
      this.getName().equals(newBarber.getName());
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO barbers (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Barber> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM barbers;";
      return con.createQuery(sql).executeAndFetch(Barber.class);
    }
  }

  public static Barber find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM barbers where id=:id;";
      Barber barber = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Barber.class);
    return barber;
    }
  }

  public void updateBarberName(String newName){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE barbers SET name = :name WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("name", newName)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM barbers WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
