# jDTO
<h3>Annotation based DTO generator for Java</h3>¨
Welcome to the jDTO wiki!

jDTO is an annotation based java code generator for DTOs or similar classes.

For examples on how to use the code see project jdto-exemples

<h1>Usage example</h1>
Here is a usage example, imagine we have an entity file like this one and would like to generate a DTO from it.

```@Entity
@Table(name = "ROOM")
public class ExampleObject {
	 private int number;
	 private Date date;
	 private Collection<String> listOfStrings;
         // more methods..
}
```
We add the annotations:
```
@GenerateDto
@Entity
@Table(name = "ROOM")
public class ExampleObject {

	 @DtoField
	 private int number;
	 
	 @DtoField
	 private Date date;
	 
	 @DtoField
	 private Collection<String> listOfStrings;
}
```

When processing this class, the DTO generator will generate this:

```
import java.sql.Date;
import java.util.Collection;

public class ExampleObjectDTO {
    public int number;
    public Date date;
    public Collection<String> listOfStrings;

    int getNumber() {
        return number;
    }

    int setNumber(int number) {
        this.number = number
    }

    Date getDate() {
        return date;
    }

    Date setDate(Date date) {
        this.date = date
    }

    Collection<String> getListOfStrings() {
        return listOfStrings;
    }

    Collection<String> setListOfStrings(Collection<String> listOfStrings) {
        this.listOfStrings = listOfStrings
    }
}
```


The project <b>jdto-examples</b> shows how to use jDTO engine at compile time with a maven plugin. Junits in this project shows how to use jDTO generator from java code. More examples and other feature to come...

