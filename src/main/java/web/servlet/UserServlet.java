package web.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@SuppressWarnings("all")
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.��ȡ����
        Map<String, String[]> map = request.getParameterMap();
        //2.��װ����
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.����Service��ѯ
        UserService service = new UserServiceImpl();
        User u = service.login(user);
        //4.�ж�
        ResultInfo info = new ResultInfo();
        if(u==null){
            //�û������������
            info.setFlag(false);
            info.setErrorMsg("�û������������");
        }
        if(u!=null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("����δ����뼤��");
        }
        if(u!=null && "Y".equals(u.getStatus())){
            info.setFlag(true);
            request.getSession().setAttribute("user",u);//��¼�ɹ����
        }
        //5.��Ӧ����
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.��֤ ��֤��
        //��ȡ�û���д����֤��
        String check = request.getParameter("check");
        //��session�л�ȡ�������ɵ���֤��
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute(checkcode_server);//��֤��֤��ֻ��һ��
        //�ж�
        if(!check.equalsIgnoreCase(checkcode_server) || checkcode_server == null){
            //��֤�����
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("��֤�����");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //1.��ȡ����
        Map<String, String[]> map = request.getParameterMap();
        //2. ��װ���� BeanUtils
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.����service���ע��
        UserService userService = new UserServiceImpl();
        ResultInfo info = new ResultInfo();
        boolean flag = userService.regist(user);
        //4.��Ӧ���
        if(flag){
            //ע��ɹ�
            info.setFlag(true);
        }else {
            //ע��ʧ��
            info.setFlag(false);
            info.setErrorMsg("ע��ʧ�ܣ�");
        }
        //5.��info�������л�Ϊjson
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //6.��json����д�ؿͻ���
        //����content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        //mapper.writeValueAsString(user);
        mapper.writeValue(response.getOutputStream(),user);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.����session�е�user����
        request.getSession().invalidate();
        //2.��ת��¼ҳ��
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.��ȡ������
        String code = request.getParameter("code");
        //2.�ж�
        if(code!=null){
            //����service��ɼ���
            UserService userService = new UserServiceImpl();
            boolean flag = userService.acitve(code);
            //�жϱ��
            String msg = null;
            if(flag){
                //����ɹ�
                msg = "����ɹ�����<a href='login.html'>��¼</a>";
            }else {
                //����ʧ��
                msg = "����ʧ�ܣ��������Ա��ϵ";
            }
            response.setContentType("text/html; Charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
