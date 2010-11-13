package org.apache.taglibs.standard.tag.compat.core;

import java.util.Collections;
import javax.el.ValueExpression;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.apache.taglibs.standard.util.ExpressionUtil;

/**
 * JSTL 1.0 compatible implementation of &lt;foreach&gt; that uses JSP EL support.
 */
public class ForEachTag extends ForEachSupport {

    private ValueExpression beginExpression;
    private ValueExpression endExpression;
    private ValueExpression stepExpression;
    private ValueExpression itemsExpression;

    public ForEachTag() {
    }

    @Override
    public void release() {
        beginExpression = null;
        endExpression = null;
        stepExpression = null;
        itemsExpression = null;
        super.release();
    }

    @Override
    public int doStartTag() throws JspException {
        if (beginSpecified) {
            begin = (Integer) beginExpression.getValue(pageContext.getELContext());
            validateBegin();
        }
        if (endSpecified) {
            end = (Integer) endExpression.getValue(pageContext.getELContext());
            validateEnd();
        }
        if (stepSpecified) {
            step = (Integer) stepExpression.getValue(pageContext.getELContext());
            validateStep();
        }
        if (itemsExpression != null) {
            rawItems = itemsExpression.getValue(pageContext.getELContext());
            if (rawItems == null) {
                rawItems = Collections.emptyList();
            }
        }
        return super.doStartTag();
    }

    public void setBegin(String begin) {
        beginExpression = ExpressionUtil.createValueExpression(pageContext, begin, Integer.TYPE);
        beginSpecified = true;
    }

    public void setEnd(String end) {
        endExpression = ExpressionUtil.createValueExpression(pageContext, end, Integer.TYPE);
        endSpecified = true;
    }

    public void setStep(String step) {
        stepExpression = ExpressionUtil.createValueExpression(pageContext, step, Integer.TYPE);
        stepSpecified = true;
    }

    public void setItems(String items) {
        itemsExpression = ExpressionUtil.createValueExpression(pageContext, items, Object.class);
    }
}
