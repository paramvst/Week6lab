package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author hsp28
 */
public class ShoppingListServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String logout = request.getParameter("logout");
        if (logout != null)
        {
//            After the session ends, I just want the user to return to the login page again.
            request.getSession().invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        Starting a new session
        HttpSession session = request.getSession();
        
//        When the user hits register name
        String action = request.getParameter("action");
        
        if (action.equals("register"))
        {
            String username = request.getParameter("username");

            if (username == null || username.equals(""))
            {
                request.setAttribute("invalid", "Username is invalid");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
                return;
            }
            else
            {
//                Making a user object
                User user = new User(username);
                session.setAttribute("sessionUser", user.getUsername());
//                Displaying the shopping list page
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
            }
        }
        
        if (action.equals("add"))
        {
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("sessionItems");
            
            if (items == null)
            {
                items = new ArrayList<>();
            }
            
            String userItem = request.getParameter("userItem");
            
            if (userItem == null || userItem.equals(""))
            {
                request.setAttribute("itemInvalid", "Please enter a valid item");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
                return;
            }
            
//            If the item is fine, add it to the arraylist and update in the session
            items.add(userItem);
            session.setAttribute("sessionItems", items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }
        
        if (action.equals("delete"))
        {
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("sessionItems");
            int count = 0;
            if (items.isEmpty())
            {
                request.setAttribute("noDel", "No items to delete. Enter the items first");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
            }
            else   
            {
                //returns apple, banana and cherry
                
                for (int i=0; i<items.size(); i++)
                {
                    String radio = request.getParameter(items.get(i)); //apple
                    for (int j=0; j < items.size(); j++)
                    {
                        if (radio != null && radio.equals(items.get(j)))
                        {
                            //Delete the items
                            items.remove(radio);
                            i--;
                            count++;
                            break;
                        }
                    }  
                }
                session.setAttribute("sessionItems", items);
                if (items.isEmpty())
                {
                    request.setAttribute("noDel", "All items deleted.");
                }
                else
                {
                    if (count > 1) 
                    {
                        request.setAttribute("noDel", count+" items deleted.");
                    }
                    else
                    {
                        request.setAttribute("noDel", count+" item deleted.");
                    }
                }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
        }
    }

}
