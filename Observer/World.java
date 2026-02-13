package Observer;

import java.beans.PropertyChangeEvent;

public class World {
    private String news;

    public void propertyChange(PropertyChangeEvent evt) {
        this.setNews((String) evt.getNewValue());
    }

    public void setNews(String value) {
        this.news = value;
        System.out.println("World News Updated: " + this.news);
    }

    public String getNews() {
        return news;
    }
}
