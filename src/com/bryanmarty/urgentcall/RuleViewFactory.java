package com.bryanmarty.urgentcall;
import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public final class RuleViewFactory {

	public RuleView createRuleView(Context context, UrgentEntry data) {
		RuleView v = new RuleView(context, data);
		v.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 50));
		return v;
	}
}
