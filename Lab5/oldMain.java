/**import java.util.Scanner; 
class oldMain { 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        int numEntries = sc.nextInt();

        Roster roster = new Roster("roster");

        for (int i = 0; i < numEntries; i++) {
            String studentName = sc.next();
            String mod = sc.next();
            String test = sc.next(); 
            String grade = sc.next();

            if (roster.get(studentName) == null) {
                roster = roster.put(new Student(studentName).put(new Module(mod).put(new Assessment(
                                    test, grade))));
            } else if (roster.get(studentName).get(mod) == null) {
                roster.get(studentName).put(new Module(mod).put(new Assessment(test, grade)));
            } else {
                roster.get(studentName).get(mod).put(new Assessment(test, grade));
            }
        }


        while (sc.hasNext()) {
            System.out.println(roster.getGrade(sc.next(), sc.next(), sc.next()));
        }

        sc.close();
    }
}
*/
