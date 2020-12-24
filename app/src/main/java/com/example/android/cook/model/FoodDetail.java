package com.example.android.cook.model;

public class FoodDetail {

    private String m_id;
    private String m_mealName;
    private String m_uTube;
    //  private String m_category;

    public FoodDetail(String id,String name,String link){
        m_id=id;
        m_mealName=name;
        m_uTube=link;
    }

    public String getId() { return m_id; }
    public String getName() { return m_mealName; }
    public String getLink() { return m_uTube; }
}
