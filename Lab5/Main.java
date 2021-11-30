import java.util.Scanner; 
import java.util.Optional; 

class Main { 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        int numEntries = sc.nextInt();

        Roster roster = new Roster("roster");


        for (int i = 0; i < numEntries; i++) {
            String studentName = sc.next();
            String mod = sc.next();
            String test = sc.next(); 
            String grade = sc.next();

            roster.get(studentName)
                .ifPresentOrElse(x -> x.get(mod)
                        .ifPresentOrElse(y -> y.put(new Assessment(test, grade)),
                            () -> x.put(new Module(mod).put(new Assessment(test, grade)))),
                    () -> roster.put(new Student(studentName)
                                         .put(new Module(mod).put(new Assessment(test, grade)))));


        }


        while (sc.hasNext()) {
            System.out.println(roster.getGrade(sc.next(), sc.next(), sc.next()));
        }

        sc.close();
    }

}
