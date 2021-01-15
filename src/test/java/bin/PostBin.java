package bin;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/06/2021(Date Format : MM/DD/YYYY)
 * Class intent : DTO class to parse response body obtained from getPosts() Api
 */

public class PostBin {
	
	private int userId;
	private int id;
	private String title;
	private String body;
	
	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
