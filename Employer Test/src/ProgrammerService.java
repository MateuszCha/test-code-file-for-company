import pl.blueenergy.document.*;
import pl.blueenergy.organization.User;
import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mateusz Charyszyn
 */

public class ProgrammerService {
	private double averageValueDouble;
	private int averageValueint;


	public void execute(DocumentDao documentDao) {
		List<ApplicationForHolidays> listHoliday = new ArrayList<ApplicationForHolidays>(20);
		List<Questionnaire> listQuestionnaire = new ArrayList<Questionnaire>(20);
		List<Document> list = documentDao.getAllDocumentsInDatabase();

		for(Document listForOF : list) {
			if (listForOF instanceof ApplicationForHolidays) {
				listHoliday.add((ApplicationForHolidays)listForOF);
				continue;
			}
			if (listForOF instanceof Questionnaire) {
				listQuestionnaire.add((Questionnaire)listForOF);
			}
		}
		((ArrayList)listQuestionnaire).trimToSize();
		((ArrayList)listHoliday).trimToSize();

		avarageValueOfQuestion(listQuestionnaire);
		checkLoginUsers(listHoliday);
		checkDate(listHoliday);
		//System.out.println(this.averageValueDouble);
		//System.out.println(this.averageValueint);
		Helper helper = new Helper(listQuestionnaire);
		relativ(listHoliday);
	}
	/**
	 *
	 * @param listQuestionnaire
	 * 1. Policz, ile średnio możliwych odpowiedzi zawierają wszystkie pytania we wszystkich kwestionariuszach w systemie.    	ok
	 */
	private void avarageValueOfQuestion(List<Questionnaire> listQuestionnaire){
		int allAnswers = 0;
		int size = 0;
		for (int i = 0; i < listQuestionnaire.size(); i++)
		{
			int j = listQuestionnaire.get(i).getQuestions().size();
			for (j -= 1; j >= 0; j--) {
				allAnswers += listQuestionnaire.get(i).getQuestions().get(j).getPossibleAnswers().size();
				size ++;
			}
		}
		this.averageValueint = this.roundInt(allAnswers, size);
		this.averageValueDouble = this.roundDouble(allAnswers, size);
	}
	/**
	 *
	 * @param value ilosc pytan
	 * @param value2 ilosc kwestionariuszy
	 * @return typu Int srednia ilosc pytan z wszystkich kwestionariuszy
	 *
	 * mozna bylo uzyc klasy Math i metody round
	 */
	private int roundInt(int value, int value2 ) {
		try
		{
			int returnValue = value/value2;
			if((value*1.0/value2 - (int)(value/value2)) >= 0.5)
				return returnValue+1;
			else
				return returnValue;

		} catch(ArithmeticException err)
		{
			StackTraceElement[] track = Thread.currentThread().getStackTrace();
			System.out.println("--------------------ERROR----------------------");
			System.out.println(err + "\nMethod call from:" + track[2].getMethodName() + " in line on " + track[2].getLineNumber() + " ");
			System.out.println("--------------------ERROR----------------------");
			return 0;
		}
	}

	/**
	 *
	 * @param value ilosc pytan
	 * @param value2 ilosc kwestionariuszy
	 * @return typu double srednia ilosc pytan z wszystkich kwestionariuszy
	 */
	private double roundDouble(int value, int value2 ){
		try {
			return (double)value / value2;
		} catch (ArithmeticException err)
		{
			StackTraceElement[] track = Thread.currentThread().getStackTrace();
			System.out.println("--------------------ERROR----------------------");
			System.out.println(err + "\n Method call from:" + track[2].getMethodName() + " in line on " + track[2].getLineNumber() + " ");
			System.out.println("--------------------ERROR----------------------");
			return 0;
		}
	}

	/**
	 *
	 * @param listHolidey cala lista uzytkownikow
	 * @return Lista uzytkownikow ktorzy zawieraja niepoprawne znaki
	 *
	 * 2. Stwórz listę wszystkich użytkowników, którzy złożyli wniosek o urlop, a następnie sprawdź czy któryś z nich w						  OK
	 *  loginie zawiera polskie znaki, które mogłyby spowodować błędy w niektórych mechanizmach. (tylko polskie)
	 *
	 *
	 */
	private List<ApplicationForHolidays> checkLoginUsers(List<ApplicationForHolidays> listHolidey)
	{
		/*
			261	ą
			263 ć
			281	ę
			322 ł
			324	ń
			243 ó
			347 ś
			378 ź
			380 ż
		 */
		List<ApplicationForHolidays> listWrongsUser = new ArrayList<ApplicationForHolidays>();
		List<Integer> elemntOnList = new ArrayList<Integer>();

		for(int i = 0 ; i < listHolidey.size() ; i++)
		{
			String login = listHolidey.get(i).getUserWhoRequestAboutHolidays().getLogin();
			for(short j = 0; j < login.length(); j++)
			{
				char c = login.charAt(j);
				if(   (int)c == 261 || (int)c == 263 || (int)c == 281 || (int)c == 322 || (int)c == 324
						|| (int)c == 243 || (int)c == 347 || (int)c == 378 ||(int)c == 378 || (int)c == 380 ) {
					listWrongsUser.add(listHolidey.get(i));
					elemntOnList.add(i);
				}
			}
		}
		((ArrayList) elemntOnList).trimToSize();
		((ArrayList) listWrongsUser).trimToSize();
		for (int i = 0; i < listWrongsUser.size(); i++)
		{
			System.out.println(((ApplicationForHolidays) listWrongsUser.get(i)).getUserWhoRequestAboutHolidays().getLogin());
			System.out.print(((ApplicationForHolidays) listWrongsUser.get(i)).getUserWhoRequestAboutHolidays().getName() + " ");
			System.out.println(((ApplicationForHolidays) listWrongsUser.get(i)).getUserWhoRequestAboutHolidays().getSurname());
			System.out.format("nr. %d w bazie danych  \n", elemntOnList.get(i));
		}
		return listWrongsUser;
	}

	/**
	 *
	 * @param list lista wszystkich urlopowiczow
	 *
	 *  3. Sprawdź, czy któryś z wniosków urlopowych zawiera niepoprawnie wprowadzony początek i koniec urlopu (tzn. daty w złej kolejności).      OK
	 */
	private void  checkDate (List<ApplicationForHolidays> list){
		List<ApplicationForHolidays> listWrongDate = new ArrayList<ApplicationForHolidays>(20);

		for(ApplicationForHolidays listForOf : list)
		{
			Date to = listForOf.getTo();
			Date since = listForOf.getSince();
			if(since.getTime() > to.getTime())
			{
				listWrongDate.add(listForOf);
				listForOf.setTo(since);
			    listForOf.setSince(to);
			}
		}
		try {
			for(ApplicationForHolidays lsit : listWrongDate)
				System.out.println(lsit.getSince() + " end " + lsit.getTo() + " " + lsit.getUserWhoRequestAboutHolidays().getName() +
									" " + lsit.getUserWhoRequestAboutHolidays().getSurname());
		}catch (Exception err){
			System.out.println(err);
		}
	}
	/**	 *
	 * @param list
	 * 5. Obiekt klasy User zawiera prywatne pole "salary" nie posiadające żadnych publicznych akcesorów. Używając refleksji	OK
	 * zmień wartość powyższego pola dla dowolnego użytkownika wnioskującego o urlop.
	 *
	 */
	private void relativ(List<ApplicationForHolidays> list) {
		Class user = list.get(1).getUserWhoRequestAboutHolidays().getClass();
		User users = list.get(1).getUserWhoRequestAboutHolidays();
		try {
			Field field = user.getDeclaredField("salary");
			field.setAccessible(true);
			field.set(users, 2.5);
		}catch (Exception err)
		{
			System.out.println(err);
		}
	}
}
