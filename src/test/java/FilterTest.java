import edu.eci.arsw.cinema.filters.impl.FilterGenre;
import edu.eci.arsw.cinema.filters.impl.FilterSeats;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class FilterTest {
    @Test
    public void filterGenre(){
        FilterGenre filterGenre =new FilterGenre();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);

        boolean bol = true;
        for(Movie movie : filterGenre.filter(c,functionDate,"Horror")){
            if(!movie.getGenre().equals("Horror")) bol = false;
        }

        assertTrue(true);
    }
    @Test
    public void filterSeats(){
        FilterSeats filterSeats = new FilterSeats();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);

        List<Movie> movies2 = filterSeats.filter(c,functionDate,"4");
        List<Movie> movies = new ArrayList<>();

        for(CinemaFunction cf : c.getFunctions()){
            int num = 0;
            for(List<Boolean> temp : cf.getSeats()){
                for(Boolean temp2 : temp){
                    if(temp2) num++;
                }
            }
            if(num>3) movies.add(cf.getMovie());
        }

        boolean bol = true;
        for(int i = 0; i<movies2.size();i++){
            if(!movies.get(i).getName().equals(movies2.get(i).getName())) bol = false;
        }
        assertTrue(bol);
    }
}
