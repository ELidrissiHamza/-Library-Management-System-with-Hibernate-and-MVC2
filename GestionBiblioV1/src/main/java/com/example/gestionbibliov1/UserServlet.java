package com.example.gestionbibliov1;

import beans.*;
import hcn.DAOAdherant;
import hcn.DAOEmprunt;
import hcn.DAOExemplaire;
import hcn.DAOLivre;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOLivre userDAO;
    private DAOAdherant adherantDAO;
    private DAOExemplaire exemplaireDAO;
    private DAOEmprunt empruntDAO;

    public void init() {
        userDAO = new DAOLivre();
        adherantDAO=new DAOAdherant();
        exemplaireDAO=new DAOExemplaire();
        empruntDAO=new DAOEmprunt();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

            try {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
                        break;
                    case "/insert":
                        insertUser(request, response);
                        break;
                    case "/delete":
                        deleteUser(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateUser(request, response);
                        break;
                    case "/retrieve":
                        retrieve(request, response);
                        break;
                    case "/list":
                        listUser(request, response);
                        break;
                    case "/loginVerfy":
                        loginVerify(request, response);
                        break;
                    case "/logout":
                        logout(request, response);
                        break;
                    case "/listadherent":
                        adherentlist(request, response);
                        break;
                    case "/newadherant":
                        showNewFormadherant(request, response);
                        break;
                    case "/insertaderent":
                        insertAdherant(request, response);
                        break;
                    case "/deleteadherant":
                        deleteAdherant(request, response);
                        break;
                    case "/editadherant":
                        showEditFormAdherent(request, response);
                        break;
                    case "/updateaderent":
                        updateAdherent(request, response);
                        break;
                    case "/retrieveadherant":
                        retrieveadherant(request, response);
                        break;
                    case "/listemprunt":
                        empruntslist(request, response);
                        break;
                    case "/newemprunt":
                        showNewFormaemprunt(request, response);
                        break;
                    case "/insertemprunt":
                        insertEmprunt(request, response);
                        break;
                    case "/deleteemrunt":
                        deleteEmprunt(request, response);
                        break;
                    default:
                        login(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }

    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Livre> listLivre = userDAO.retreive();
        request.setAttribute("listLivre", listLivre);
        HttpSession session=request.getSession();
        String admin = (String) session.getAttribute("username");
        String page=null;
        if(admin.equals("admin")) page="user-list.jsp";
        else page="user-list-user.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String isbn = request.getParameter("id");
        Livre existingUser = userDAO.findByIsbn(isbn);
        System.out.println("***"+existingUser.getTitre());
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");

        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String msg="";
        String isbn = request.getParameter("name");
        String titre = request.getParameter("email");
        int nbrex= Integer.parseInt(request.getParameter("nbrex"));
        isbn.trim();
        titre.trim();
        Livre newLivre = new Livre(isbn, titre);
       boolean b=userDAO.create(newLivre);
        int n=exemplaireDAO.retreive().size();
        for(int i=0;i<nbrex;i++) exemplaireDAO.create(new Exemplaire(n+i,newLivre));

        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("isbn:" + request.getParameter("id"));
        System.out.println("titre:" + request.getParameter("email"));
        String isbn = request.getParameter("id");
        String titre = request.getParameter("email");
        isbn = isbn.trim();
        titre = titre.trim();
        Livre book = new Livre(isbn, titre);
        userDAO.update(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String isbn = request.getParameter("id");
        userDAO.delete(userDAO.findByIsbn(isbn));
        System.out.println("deleted!");
        response.sendRedirect("list");

    }

    private void retrieve(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String isbn = request.getParameter("isbn");
        isbn = isbn.trim();
        Livre l = userDAO.findByIsbn(isbn);
        List<Livre> listLivre = new ArrayList<>();
        Object a = (l == null) ? listLivre = userDAO.retreive() : listLivre.add(l);
        request.setAttribute("listLivre", listLivre);
        HttpSession session=request.getSession();
        String admin = (String) session.getAttribute("username");
        String page=null;
        if(admin.equals("admin")) page="user-list.jsp";
        else page="user-list-user.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loginVerify(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        if ((user.equals("admin") && password.equals("admin"))||(user.equals("hamza") && password.equals("hamza"))||(user.equals("vall") && password.equals("vall"))) {
            HttpSession session=request.getSession();
            session.setAttribute("username",user);
            try {
                listUser(request,response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        session.removeAttribute("username");
        session.invalidate();
        try {
            response.sendRedirect("login.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void adherentlist(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Adherant> listAdherant = (List<Adherant>) adherantDAO.retreive();
        request.setAttribute("listAdherant", listAdherant);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adherent-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewFormadherant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("adherant-form.jsp");
        dispatcher.forward(request, response);
    }
    private void insertAdherant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String cin = request.getParameter("cin");
        String name = request.getParameter("lastname");
        String firstname=request.getParameter("firstname");
         cin = cin.trim();
        name=name.trim();
        firstname=firstname.trim();
        Adherant newAdherant = new Adherant(name, firstname,cin);
        adherantDAO.create(newAdherant);
        response.sendRedirect("listadherent");
    }
    private void deleteAdherant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String cin = request.getParameter("id");
        System.out.println("%%%%%" + cin);
        adherantDAO.delete(adherantDAO.findByCin(cin));
        response.sendRedirect("listadherent");

    }
    private void showEditFormAdherent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String cin = request.getParameter("id");
        Adherant existingAdherent = adherantDAO.findByCin(cin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adherant-form.jsp");
        request.setAttribute("user", existingAdherent);
        dispatcher.forward(request, response);

    }
    private void updateAdherent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String cin = request.getParameter("cin");
        String name = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");

        cin = cin.trim();
        name = name.trim();
        firstname = firstname.trim();
        Adherant adh = new Adherant(name, firstname,cin);
        adherantDAO.update(adh);
        response.sendRedirect("listadherent");
    }
    private void retrieveadherant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String cin = request.getParameter("cin");
        cin = cin.trim();
        Adherant a = adherantDAO.findByCin(cin);
        List<Adherant> lisadherent = new ArrayList<>();
        Object b = (a == null) ? lisadherent = (List<Adherant>) adherantDAO.retreive() : lisadherent.add(a);
        request.setAttribute("listAdherant", lisadherent);
        HttpSession session=request.getSession();
        String admin = (String) session.getAttribute("username");
        RequestDispatcher dispatcher = request.getRequestDispatcher("adherent-list.jsp");
        dispatcher.forward(request, response);
    }
    public void empruntslist(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List<Emprunt> listEmprunts = (List<Emprunt>) empruntDAO.retreive();
        List<Vector<Object>> lists=new ArrayList<>() ;
        for(Emprunt e :listEmprunts )
        {
            Vector v = new Vector();
            v.add(e.getId().getCin());
            v.add(exemplaireDAO.getIsbn(e.getId().getIdexmp()));
            v.add(e.getId().getIdexmp());
            v.add(e.getId().getDateemp());
            v.add(e.getRetourne());
            System.out.println(e.getId().getDateemp());
            lists.add(v);

        }
        request.setAttribute("lists", lists);
        RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewFormaemprunt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Adherant> listAdherant = (List<Adherant>) adherantDAO.retreive();
        request.setAttribute("listAdherant", listAdherant);
        List<Livre> listLivre = userDAO.retreive();
        request.setAttribute("listLivre", listLivre);
        RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt-form.jsp");
        dispatcher.forward(request, response);
    }
    protected void insertEmprunt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg="";
        String isbn = request.getParameter("isbn");
        String cin = request.getParameter("cin");
        isbn=isbn.trim();
        cin=cin.trim();
        Livre L=userDAO.findByIsbn(isbn);
        Adherant ads=adherantDAO.findByCin(cin);
        Exemplaire exp=exemplaireDAO.getAvailableExp(isbn);
        if(ads==null) msg="Adherent n'exist pas";
        else if(L== null) msg="Livre n'exist pas";
        else if(exp==null) msg= "y'a pas d'exempalire disponible";
        else  msg=empruntDAO.create(new Emprunt(new EmpruntId(exp.getIdexp(), ads.getCin(), LocalDate.now().toString() )))?"Emprunt bien enregistree":"probleme d'enregistrement";
        response.sendRedirect("listemprunt");
    }
    public void deleteEmprunt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg="";
        String cin = request.getParameter("id");
        int id = Integer.parseInt(request.getParameter("idex"));
        String date = request.getParameter("date");
        EmpruntId idemp=new EmpruntId(id,cin, date);
        msg=empruntDAO.delete(new Emprunt(idemp, 0))?"Emprunt retournee":"probleme !!!";
        System.out.println(msg);
        response.sendRedirect("listemprunt");
    }


}