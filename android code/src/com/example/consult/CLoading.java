package com.example.consult;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

/**
 * Ŀ���ҷε� Ŭ����
 * @author YT
 */
public class CLoading{
    private static Dialog m_loadingDialog = null;

    public static void showLoading(Context context) {

        if (m_loadingDialog == null) {    
            //���̾�αװ� ������ ����� ���̰� �϶�
            m_loadingDialog = new Dialog(context, R.style.custom_loading);
            //���α׷����� ��������
            ProgressBar pb = new ProgressBar(context);
            LayoutParams params = new LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //���α׷����� ���̾�α׿� ��������
            m_loadingDialog.setContentView(pb, params);
            m_loadingDialog.setCancelable(false);
            m_loadingDialog.show();    
        } else if(!m_loadingDialog.isShowing()){    
            //���̾�αװ� �ִµ� HIDE ���¸� ���̰� �϶�            
            m_loadingDialog = null;
            m_loadingDialog = new Dialog(context, R.style.custom_loading);
            ProgressBar pb = new ProgressBar(context);
            LayoutParams params = new LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            m_loadingDialog.setContentView(pb, params);
            m_loadingDialog.setCancelable(false);
            m_loadingDialog.show();
        }

    }
    public static void hideLoading() {
        if (m_loadingDialog != null) {
            if(m_loadingDialog.isShowing()){
                //���̾�αװ� �ְ� ���̴� ���¸� �Ⱥ��̰� �϶�    
                m_loadingDialog.dismiss();
                m_loadingDialog = null;
            }    
        }
    }
}