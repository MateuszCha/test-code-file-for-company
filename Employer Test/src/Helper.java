import pl.blueenergy.document.Question;
import pl.blueenergy.document.Questionnaire;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @param path przechowuje sciezke do pliku
 * @parma list inicjalizowana jest lisat obiektow typu Questionnaire
 *
 * @Warning
 * 	Do Sicezki dodawana jest data utworzenia pliku w formacie /DD_MM_YYYY.txt
 *
 * 4. Stwórz mechanizm (Helper lub Service), który pozwoli w wygodny sposób zapisać obiekt klasy Questionnaire do pliku       				  OK
 * tekstowego w czytelnym dla użytkownika formacie, czyli np:
 *
 * Pytanie: Jaki jest Twój ulubiony kolor?
 *     1. Żółty
 *     2. Zielony
 *     3. Niebieski
 */
public class Helper {
    private final String path = "C:\\Users\\Media\\Desktop\\test\\";
    private List<Questionnaire> list;

    public Helper(List<Questionnaire> list)
    {
        this.list = list;
        if(this.saveFile())
            System.out.println("zapisano do pliku");
        else
            System.out.println("Nie zapisano do pliku");
    }

    private boolean saveFile() {
        String puseLine = "*****************************************************************************************";
        String newLine = String.valueOf((char)13)+(char)10;
        String tabLine = String.valueOf((char) (9));

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");
        String fullpath = this.path + format.format(date) + ".txt";
        try {
            BufferedOutputStream outP = new BufferedOutputStream(new FileOutputStream(fullpath));
            for(Questionnaire listForOf : this.list)
            {
                outP.write(tabLine.getBytes());
                outP.write(tabLine.getBytes());
                outP.write(listForOf.getTitle().getBytes());
                outP.write(newLine.getBytes());
                for(Question lifsQuestion : listForOf.getQuestions()) {
                    outP.write(newLine.getBytes());
                    outP.write("Pytanie: ".getBytes());
                    outP.write(lifsQuestion.getQuestionText().getBytes());
                    for (int i = 0; i < lifsQuestion.getPossibleAnswers().size(); i++) {
                        outP.write(newLine.getBytes());
                        outP.write("\n".getBytes());
                        outP.write(tabLine.getBytes());
                        outP.write((String.valueOf(i+1)+". ").getBytes());
                        outP.write(lifsQuestion.getPossibleAnswers().get(i).getBytes());
                    }
                    outP.write(newLine.getBytes());
                }
                outP.write(newLine.getBytes());
                outP.write(newLine.getBytes());
                outP.write(puseLine.getBytes());
                outP.write(newLine.getBytes());
            }
            outP.flush();
            outP.close();
        }catch (IOException err) {
            err.printStackTrace();
            return false;
        }
        return true;
    }
}

