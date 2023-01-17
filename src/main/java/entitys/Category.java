package entitys;

public enum Category {
    ONKOLOGIST (1,"onkologist"),
    TRAUMATOLOGIST (2, "traumatologist"),
    OPHTHALMOLOGIST (3, "ophthalmologist"),
    DENTIST (4, "dentist"),
    PSYCHIATRIST (5, "psychiatrist"),
    THERAPIST (6, "therapist"),
    PEDIATRICIAN (7, "pediatrician");

    private final int id;
    private final String title;


    Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTitleId() {
        return id;
    }

    public static Integer getIDByName(String title) {
        if (title.equals("onkologist")){
           return 1;
        }
        else if (title.equals("traumatologist")){
            return 2;
        }
        else if (title.equals("ophthalmologist")){
            return 3;
        }
        else if (title.equals("dentist")){
            return 4;
        }
        else if (title.equals("psychiatrist")){
            return 5;
        }
        else if (title.equals("therapist")){
            return 6;
        }
        else if (title.equals("pediatrician")){
            return 7;
        }
        else {
            throw new IllegalArgumentException("Incorrect id, do not have role for this");
        }
    }
}





