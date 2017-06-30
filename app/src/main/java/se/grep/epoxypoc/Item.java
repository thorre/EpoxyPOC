package se.grep.epoxypoc;

class Item {
    private long id;
    private String title;

    public Item(int id, String tittle) {
        this.id = id;
        title = tittle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
