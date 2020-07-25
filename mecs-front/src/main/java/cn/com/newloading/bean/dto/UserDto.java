package cn.com.newloading.bean.dto;

import cn.com.newloading.bean.Card;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.User;

public class UserDto extends User {
	private Card card;
	private Dict dict;

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(Card card, Dict dict) {
		super();
		this.card = card;
		this.dict = dict;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDto [card=");
		builder.append(card);
		builder.append(", dict=");
		builder.append(dict);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
