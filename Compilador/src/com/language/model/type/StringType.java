package com.language.model.type;

public class StringType extends Type{
	private String text;

	public StringType(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
    public void setText(String t){
        text=t;
    }

    public int getType(){
        return 3;   //3-TypeString
    }

    public void print(){
    }
    
	@Override
	public TypeEnum getTypeEnum() {
		return TypeEnum.STRING;
	}
	
	@Override
	public String getAsString() {
		return text;
	}
	
	public boolean getBooleanValue(){
		return text.isEmpty();
	}




	public boolean equals(StringType obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringType other = (StringType) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	
}
