package net.ordesk.ordesk_vivino;

import static java.lang.Boolean.FALSE;

/**
 * Created by USER1 on 21-Nov-16.
 */

public class feedback_answers {

    boolean q1_ans = FALSE, q2_ans = FALSE, q3_ans = FALSE;

    public void set_ans(int q , boolean ans)
    {
        if(q==1){q1_ans = ans;}
        if(q==2){q2_ans = ans;}
        if(q==3){q3_ans = ans;}
    }

    public boolean get_ans(int q)
    {
        if(q==1){return q1_ans;}
        if(q==2){return q2_ans;}
        if(q==3){return q3_ans;}
        return FALSE;
    }
}
