	import java.util.*;
	import java.io.*;
	import java.lang.*;
public class fiNAL {

		public static void main(String[] args) throws IOException {
			String username = getUsername();// Goes to getUsername method and returns a variable,then stored
		    String password = getPassword(); 
		    int count = 1;
		    String[] userAnswers = new String[10];//stores the users answers
		    String[] answers = new String[10];
		    File stat = new File("student.txt");// naming the file for when it prints out the stats
		    FileWriter filewr = new FileWriter("studentstats.txt", true);//starts from the end of the file instead of rewriting
		    PrintWriter outStats = new PrintWriter(filewr);
		    
		    
		    while(count <= 2 && !(username.equalsIgnoreCase("done"))) {
		      if(!studentInformation(username, password)) //line 139 & 147
		      {
		        System.out.println("Username/Password incorrect. Try again.");
		        username = getUsername();
		        password = getPassword();
		        count++;
		      }
		      
		      if(instructorInformation(username, password).equalsIgnoreCase("student"))// role is returned as instructorInformation
		      {   long startTime = 0;
		          long endTime = 0;
		          int score = 0;
		          System.out.println();
		          System.out.println("Welcome back " + username + "!");
		          System.out.println();
		          System.out.println("*** The quiz will start! ***");
		          System.out.println("*** Answer 10 random questions. ***");
		          System.out.println("*** Enter True or False (T/F) for every question. ***");
		          System.out.println();
		          startTime = System.currentTimeMillis();
		          for (int i = 1; i <= 10; i++) {//starts the quiz
		            System.out.print(i + ") ");
		            answers[i-1] = readQuizQuestions(); //stores the answers //later stores the correct answer
		            userAnswers[i-1] = getTrueFalse(); // stores the users answer
		          }
		          endTime = System.currentTimeMillis();
		          for (int i = 0; i < 10; i++) { //checking to see if its correct if so add to the score
		            if (userAnswers[i].equalsIgnoreCase(answers[i])) {
		              score++;
		            }
		            else if (answers[i].equals("TRUE") && userAnswers[i].equalsIgnoreCase("T")) {
		              score++;
		            }
		            else if (answers[i].equals("FALSE") && userAnswers[i].equalsIgnoreCase("F")) {
		              score++;
		            }
		          }
		          System.out.println("\n");
		          System.out.println("*** CONGRATS, Here are your quiz results! ***");
		          String firstname = getFirstname(password);
		          String lastname = getLastname(firstname);
		          System.out.println(firstname);
		          System.out.println(lastname);
		          System.out.println(score + " / 10");
		          System.out.println("Elasped Time: " + ((endTime - startTime)/1000) + " secs");
		          System.out.println();
		          System.out.println("Your Answers. \tCorrect Answers.");
		          for (int i = 0; i < 10; i++) {
		            System.out.println(userAnswers[i] + "\t\t" + answers[i]);
		          }
		          
		          PrintWriter outputFile = new PrintWriter(username + "COSC236_April14");
		          outStats.println(firstname);
		          outputFile.println(firstname);
		          outputFile.println(lastname);
		          outStats.println((endTime - startTime)/1000);
		          outStats.println(score);
		          outputFile.println(score + " / 10");
		          outputFile.println("Elasped Time: " + ((endTime - startTime)/1000) + " secs");
		          outputFile.println("Your Answers. \tCorrect Answers.");
		          for (int i = 0; i < 10; i++) 
		          {
		            outputFile.println(userAnswers[i] + "\t\t" + answers[i]);
		          }
		          outputFile.close();
		          outStats.close();
		          System.out.println();
		          System.out.println("Enter another username and password. Enter 'done' when finished.");
		          username = getUsername();
		          if (username.equalsIgnoreCase("done")) {
		            count = 3;
		          }
		          else {
		            password = getPassword();
		          }
		       }
		      
		      else if (instructorInformation(username, password).equals("Instructor")) { //216
		        System.out.println();
		        System.out.println("Welcome to the Instructor Menu.");
		        System.out.println("Enter the number to complete the task from the menu: ");
		        System.out.println("1)\tRegister a New Student.");
		        System.out.println("2)\tDisplay Statistics.");
		        System.out.println("3)\tAdd a New Question to the Quiz.");
		        int menuNum = getMenuNum();
		        switch (menuNum) {
		          case 1: 
		            char[] pw = {'A','B','C','D','E','F','G','H','I','J','K','M','N','O',
		            'P','Q','R','S','T','U','V','W','X','Y','Z'};
		            System.out.println("*Registering a New Student*");
		            registerStudent(pw);
		            System.out.println("The students information was entered!");
		            break;
		          case 2:
		            System.out.println("*Student Statistics of the Test*" +username);
		            getShortestDuration(); //349
		            getHighLowAvgScore(); //367
		            break;
		          case 3:
		            System.out.println("*Entering a New Test Question*");
		            System.out.println("Enter the nunber to either enter or read a question.");
		            System.out.println("1)\tManually enter the test question to add to the test bank.");
		            System.out.println("2)\tRead a queston from a file to add to the test bank.");
		            int questionNum = getMenuNum(); //182 //becomes x and checks with questionNum
		            addNewQuestions(questionNum);//309
		            break;
		        }
		        System.out.println();
		        System.out.println("Enter another username and password. Enter 'done' when finished.");
		          username = getUsername();
		          if (username.equalsIgnoreCase("done")) {
		            count = 3;
		          }
		          else {
		            password = getPassword();
		          }
		      }
		      
		    }
		  }
		  
		  public static String getUsername () {
		    Scanner scan = new Scanner (System.in);
		    System.out.print("Enter username: ");
		    String username = scan.next();
		    return username;
		  }
		  
		  
		  public static String getPassword () {
		    Scanner scan = new Scanner (System.in);
		    System.out.print("Enter password: ");
		    String password = scan.next();
		    return password;
		  }
		  
		  
		  public static String getFirstname (String pw) throws IOException {
		    File myFile = new File ("UsersInfo_101(1).txt");// reads the file to find the first name
		    Scanner input = new Scanner (myFile);
		    String fn = "";
		    while (input.hasNext()) {//loop keeps going till the textfile is over
		      String info = input.next();
		      if (info.equals(pw)) {
		        fn = input.next();
		      }
		    }
		    return fn;
		  }
		  
		  
		  public static String getLastname (String fn) throws IOException {
		    File myFile = new File ("UsersInfo_101(1).txt");
		    Scanner input = new Scanner (myFile);
		    String ln = "";
		    while (input.hasNext()) {
		      String info = input.next();
		      if (info.equals(fn)) {
		        ln = input.next();
		      }
		    }
		    return ln;
		  }
		  
		  public static int getMenuNum() {
		    Scanner scan = new Scanner(System.in);
		    int x = scan.nextInt();
		    while(x != 1 && x != 2 && x != 3) {
		      System.out.println("Error! Input correct number in menu to complete an action: ");
		      x = scan.nextInt();
		    }
		    return x;
		  }
		  
		  public static boolean studentInformation (String username, String password) throws IOException {
		    File myFile = new File ("UsersInfo_101(1).txt");
		    Scanner input = new Scanner (myFile);
		    String user = username;
		    String pw = password;
		    boolean checkUser = false;
		    boolean checkPW = false;
		    while(input.hasNext()){
		      String x = input.next();
		      if(x.equalsIgnoreCase(user)) {
		        checkUser = true;
		        x = input.next();
		        if(x.equalsIgnoreCase(pw)) {
		          checkPW = true;
		        }
		      }
		    }  
		    boolean authorized = checkUser && checkPW;
		   
		    input.close();
		    return authorized;
		  }
		  
		  
		  public static String instructorInformation(String user, String pass) throws IOException {
		    boolean instructAuth = false;
		    String role = "";
		    File myFile = new File ("UsersInfo_101(1).txt");
		    Scanner input = new Scanner (myFile);
		    while(input.hasNext()) {
		      String x = input.next();
		      if(x.equals(user)) { //
		        x = input.next();
		        if(x.equals(pass)) {
		          for(int i=0; i<4; i++) {
		            x = input.next();
		          }
		          role = x;
		        }
		      }
		    }
		    return role;
		            
		       
		  }
		  
		  public static String readQuizQuestions () throws IOException {
		    File myFile = new File ("TestBank(1)-2.txt");//questions
		    Scanner input = new Scanner (myFile);
		    Scanner scan = new Scanner (System.in);
		    Random ran = new Random();
		    int questionNum = ran.nextInt(125);
		    String answer = "";
		 
		    for (int i = 1; i <= questionNum; i++) {
		      String question = input.nextLine();
		      if (i == questionNum) {
		        System.out.print(question);
		        answer = getCorrectTF(questionNum);//227 //the answer is then returned as CorrectTF as string
		      }
		    }
		    return answer;
		  }
		  
		  
		  public static String getTrueFalse () {
		    Scanner keyboard = new Scanner (System.in);
		    String userAnswer = keyboard.nextLine();
		    while (!userAnswer.equalsIgnoreCase("T") && !userAnswer.equalsIgnoreCase("True") && 
		               !userAnswer.equalsIgnoreCase("F") && !userAnswer.equalsIgnoreCase("False")) {
		      System.out.println("Error! Enter True/False (T/F) correctly.");
		      userAnswer = keyboard.nextLine();
		    }
		    return userAnswer;
		  }
		  
		  
		  public static String getCorrectTF (int questionNum) throws IOException {
		    File myFile = new File ("Answers(1)-2.txt");
		    Scanner input = new Scanner (myFile);
		    String answer = "";
		    for (int i = 1; i <= questionNum; i++) {
		      answer = input.nextLine();
		      if (i == questionNum) {
		        answer = input.nextLine();
		      }
		    }
		    return answer;//returns the correct answer to the question
		  }
		  
		  
		  public static void registerStudent(char[] array) throws IOException {
		    Scanner scan = new Scanner(System.in);
		    Random rand = new Random();
		    String fn, ln, un, email;
		    String pw = "";
		    System.out.println("Enter the student's first name: ");
		    fn = scan.nextLine();
		    System.out.println("Enter the student's last name: ");
		    ln = scan.nextLine();
		    System.out.println("Enter the student's username: ");
		    un = scan.nextLine();
		    System.out.println("Enter the student's email address: ");
		    email = scan.nextLine();
		    for(int i = 0; i < 6; i++) { // generating the new password
		    	char x = array[rand.nextInt(25)];
		      pw = pw + x;//concatenate password
		    }
		    FileWriter filewrite = new FileWriter("UsersInfo_101(1).txt", true);//to open and turn on append mode
		    PrintWriter outputFile = new PrintWriter(filewrite);
		    outputFile.println();
		    outputFile.print(un + "\t" + pw + "\t" + fn + "\t" + ln + "\t" + email + "\tStudent");
		    outputFile.close();
		  }

		  public static void addNewQuestions(int num) throws IOException {
		     Scanner scan = new Scanner(System.in);
		     FileWriter fw1 = new FileWriter("TestBank(1)-2.txt", true);
		     FileWriter fw2 = new FileWriter("Answers(1)-2.txt", true);
		     PrintWriter outfileQue = new PrintWriter(fw1);
		     PrintWriter outfileAns = new PrintWriter(fw2);
		     switch (num) {
		          case 1:
		               System.out.println("Enter the new test question you would like to add: ");
		               String question1 = scan.nextLine();
		               outfileQue.println(question1);
		               System.out.println("Enter the answer to the new question: ");
		               String answer1 = scan.nextLine();
		               outfileAns.println(answer1);
		               outfileQue.close();
		               outfileAns.close();
		               break;
		          case 2:
		               System.out.println("Enter the name of the file to be read to get new test questions: ");
		               String file = scan.nextLine();
		               File myfile = new File(file);
		               Scanner input = new Scanner(myfile);
		               String question2 = "";
		               String answer2 = "";
		               while (input.hasNext()) {
		                    question2 = input.nextLine();
		                    answer2 = input.nextLine();
		                    outfileQue.println(question2);
		                    outfileAns.println(answer2);
		               }
		               outfileQue.close();
		               outfileAns.close();
		               break;
		          default:
		               System.out.println("Invalid number to start entering a test question.");
		               break;
		          
		     }
		  }
		  
		  public static void getShortestDuration() throws IOException {
		    File myfile = new File("studentstats.txt");
		    Scanner input = new Scanner(myfile);
		    String fn = input.next();
		    long minDur = input.nextLong();
		    input.nextInt();
		    while (input.hasNext()) {
		      String tempfn = input.next();
		      long temp = input.nextLong();
		      if (minDur > temp) {
		        minDur = temp;
		        fn = tempfn;
		      }
		      input.nextInt();
		    }
		    System.out.println("Shortest Duration of the Test: " + fn + " -- " + minDur + "secs");
		  }
		  
		  public static void getHighLowAvgScore() throws IOException {
		    File myfile = new File("studentstats.txt");
		    Scanner input = new Scanner(myfile);
		    input.next();
		    input.nextLong();
		    int high = input.nextInt();// first attempt
		    int low = high;// first attempt
		    int sum = high;// first attempt
		    int count = 1;
		    while (input.hasNext()) { // as long as there is something in the file
		      input.next();//name
		      input.nextLong();//duration
		      int temp = input.nextInt();//2nd attempt
		      sum += temp;//2nd attempt
		      if (high < temp) { //compares the first and second
		        high = temp;
		      }
		      if (low > temp) {
		        low = temp;
		      }
		      count++;
		    }
		    int avg = sum/count;
		    System.out.println("Highest Grade in the Class: " + high + "/10");
		    System.out.println("Lowest Grade in the Class: " + low + "/10");
		    System.out.println("Average Grade of the Class: " + avg + "/10");
		}

	}

