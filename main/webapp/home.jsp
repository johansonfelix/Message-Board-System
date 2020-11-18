<%@ page import="java.util.LinkedList" %>
<%@ page import="BusinessLayer.Post" %>

<%--
  Created by IntelliJ IDEA.
  User: felix
  Date: 2020-11-15
  Time: 5:13 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>MessageBoard | Home </title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">



<!--For Input Tags -->
<!-- Stylesheet -->


<!-- JavaScript -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous">
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous">
</script>
<script src="tagsinput.js"></script>


<!-- For DateRangePicker-->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<link rel="stylesheet" href="sample.css">
<link rel="stylesheet" href="stylesheet.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">



<body>

<!-- Navbar -->
<div class="topbar">
    <div class="nav">
        <!--Come back to the small nav-->

        <a class="bar-item button hide-medium hide-large hamburger-menu" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
        <a href="#" class="logo bar-item button"><img src="logo.png" class="logo header-item header-btn" alt="logo" width="200px"></a>

        <div class="search-dropdown-hover hide-small">
            <button onclick="myFunction()" class="search-btn bar-item button hide-small"><i class="fa fa-search"></i>  Search</button>
            <div id="myDropdown" class="search-dropdown-content">
                <!--Insert Servlet name-->
                <form action="FileDownloadServlet" method="POST">
                    <input type="hidden"  name="ACTION" value="SEARCH">

                    <p class="small"><label>Search Filters</label>
                    <p>

                    <div class="input">
                        <label   class="small">Search by user</label><br>
                        <input type="text" id="userfilter" name="user" placeholder="Search by user">
                    </div>

                    <div class="input">
                        <label class="small">Search by date</label><br>
                        <input type="text" id="datefilter"  name="datefilter" placeholder="Date Range" />
                    </div>


                    <div class="input">
                        <label class="small">Search by hastags</label><br>
                        <input type="text" data-role="tagsinput" id="hashtags" placeholder="# Hashtag first" name="hashtags"  title="Enter hashtag sign first">
                    </div>


                    <button class="btn button applybtn" type="submit" name="button">Apply</button>

                </form>
            </div>
        </div>




        <div class="dropdown-hover hide-small">
            <button class="bar-item button acct-btn">  <img src="avatar.png" class="w3-circle" style="height:23px;width:23px" alt="Avatar"> Username <i class="fas fa-caret-down"></i></button>
            <div class="acct-content">
                <a href="#" class="bar-item button">My profile</a>
                <a href="#" class="bar-item button">User Settings</a>
                <a href="#" class="bar-item button">Log Out</a>
            </div>
        </div>



        <!--come back to notfication number badge-->

        <a href="#" class=" bar-item button nav-btn" title="Notifications"><i class="fa fa-bell"></i><span class="w3-badge w3-right small w3-green">3</span></a>


        <a href="#" class="bar-item button nav-btn" title="Messages"><i class="fa fa-envelope"></i></a>
        <a href="#" class="bar-item button nav-btn" title="Account Settings"><i class="fa fa-user"></i></a>
        <a href="#" class="bar-item button nav-btn" title="News"><i class="fas fa-globe-americas"></i></a>
    </div>
</div>






<!-- Page Container -->
<div class="container content" style="max-width:1400px;margin-top:80px">
    <!-- The Grid -->
    <div class="row">
        <!-- Left Column -->
        <div class="col left">
            <!-- Profile -->
            <div class="profile card white">
                <div class="container">
                    <h4 class="center">My Profile</h4>
                    <p class="center"><img src="avatar.png" style="height:106px;width:106px" alt="Avatar"></p>
                    <hr>
                    <p><i class="fa fa-pencil fa-fw margin-right text-format"></i> Designer, UI</p>
                    <p><i class="fa fa-home fa-fw margin-right text-format"></i> London, UK</p>
                    <p><i class="fa fa-birthday-cake fa-fw margin-right text-format"></i> April 1, 1988</p>
                </div>
            </div>
            <br>


            <br>

            <!--remove w3 tags-->
            <!-- Interests -->
            <div class="card white hide-small">
                <div class="container">
                    <p>Popular topics</p>
                    <p>
                        <span class="tag small w3-theme-d5">US Politics</span>
                        <span class="tag small w3-theme-d4">COVID19</span>
                        <span class="tag small w3-theme-d3">NBA</span>
                        <span class="tag small w3-theme-d2">Sports</span>
                        <span class="tag small w3-theme-d1">Celebrities</span>
                        <span class="tag small w3-theme">Food</span>
                        <span class="tag small w3-theme-l1">Art</span>
                        <span class="tag small w3-theme-l2">Cars</span>
                        <span class="tag small w3-theme-l3">Computer Science</span>
                        <span class="tag small w3-theme-l4">Tech</span>
                        <span class="tag small w3-theme-l4">Nature</span>
                    </p>
                </div>
            </div>
            <br>
            <!-- End Left Column -->
        </div>

        <!-- Middle Column -->
        <div class="col middle">

            <div class="row row-padding">
                <div class="col status">
                    <div class="card white">
                        <div class="container padding-small">
                            <h6 class="opacity">Welcome User!</h6>
                            <form id="post-form" action="FileDownloadServlet" method="POST">
                                <input type="hidden" name="ACTION" value="POST">
                                <input type="text" class="post-title-input"  name="title" placeholder="Start with an interesting Post Title" required> <br>
                                <textarea class=" msg border row-padding" form="post-form"  name="message"  placeholder="What 's on your mind?" required></textarea>
                                <div class="attachments">
                                    <label>Add to your post:</label> <input type="file" name="datafile" size="50">

                                </div>
                                <br>

                                <button class="btn button" type="submit" name="button">Post</button>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!--POSTS-->
            <%
                LinkedList<Post> messageBoard = (LinkedList<Post>) request.getAttribute("messages");


                if(messageBoard.isEmpty())
                {
            %>
            <div class="container card white"><br>

                <p>No Posts to show</p>


            </div>
            <%


            }


            else {


                for (Post post : messageBoard) {  %>


            <div class="container card white"><br>
                <img src="avatar.png" alt="Avatar" class="left  margin-right" style="width:60px">
                <input type="hidden" name="postID" id="postID" value="<%=post.getPostID()%>">
                <span class="right opacity">Posted on Insert time here </span>
                <% if(post.isUpdated()) {%>
                <br><span class="right opacity"><h6>Updated on Insert time here </h6></span>
                <%}%>
                <h4><%=post.getUser()%></h4><br>
                <hr>
                <p id="title1"><%=post.getPostTitle()%></p>
                <p id="post1"><%=post.getMessage()%></p>


                <!--insert iframe for attachment-->

                <div class="row-padding" style="margin:0 -16px">
                    <img src="nature.jpg" style="width:100%" class="margin-bottom">
                </div>
                <button type="button" class="button abtn margin-bottom"><i class="fa fa-thumbs-up"></i>  Like</button>
                <button type="button" class="button abtn margin-bottom"><i class="fa fa-comment"></i>  Comment</button>


                    <%-- INPUT SESSION USER FOR UPDATING DEOLETING THEIR POSTS CAPABILITIES (uncomment the if closing tag when this is fixed)
                    <%if(post.getUser().equalsIgnoreCase(session username)){%>
                    --%>

                <%--add code to get download link of post attachment to show in update popup--%>
                <div class="right">
                    <button type="button" onclick="document.getElementById('id01').style.display='block'; document.getElementById('title').value = document.getElementById('title1').innerHTML; document.getElementById('textarea').value = document.getElementById('post1').innerHTML;"><i class="fa fa-edit"></i></button>
                    <button type="button" onclick="document.getElementById('id02').style.display='block'"><i class="fas fa-trash-alt"></i></button>
                </div>

                <!-- Modal content-->
                <div id="id01" class="modal">
                    <div class="modal-content">
                        <div class="container">
                            <span onclick="document.getElementById('id01').style.display='none'" class="button display-topright">&times;</span>

                            <div class="container padding-small">
                                <h6 class="opacity">Update Post</h6>
                                <form action="FileDownloadServlet" method="POST" id="update-form">
                                    <input type="hidden" name="postID" value="<%=post.getPostID()%>">
                                    <input type="hidden" name="date_created" value="<%=post.getDate_created()%>">
                                    <input type="hidden" name="ACTION" value="UPDATE">
                                    <input type="text" class="post-title-input" name="title" id="title" placeholder="Start with an interesting Post Title" value="" required> <br>
                                    <textarea id="textarea" class=" msg border row-padding " form="update-form" placeholder="What's on your mind?" name="update-message" required></textarea>

                                    <div class="attachments">
                                        <p> Current post attachment: <a href="">Some Link</a></p>

                                        <label>Update attachment:</label> <input id="attachment" type="file" name="datafile" size="50">

                                    </div>
                                    <br>

                                    <button class="btn button" type="submit" name="button">Post</button>

                                </form>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- Modal content-->
                <div id="id02" class="modal">
                    <div class="modal-content">
                        <div class="w3-container">

                            <h6 class="opacity">Delete Post</h6>
                            <p>Are you sure you want to delete this post?</p>
                            <form action="FileDownloadServlet" method="POST">
                                <input type="hidden" name="ACTION" value="DELETE">
                            <input type="hidden" name="postID" value="<%=post.getPostID()%>">

                            <button class="btn button nobtn right margin-right margin-bottom" onclick="document.getElementById('id02').style.display='none'" type="button" name="button">No</button>
                            <button class="btn button yesbtn right margin-right margin-bottom" type="submit" name="yes-btn">Yes</button>
                            </form>
                        </div>
                    </div>
                </div>

                <%--<%}%> --%>



            </div>
            <%



                    }
                }
            %>





        </div>



        <!-- End Middle Column -->


        <!-- Right Column -->
        <div class="col ad">


            <div class="card white padding center">
                <p>AD</p>
            </div>
            <br>

        </div>
    </div>
    <!-- End Right Column -->


    <!-- End Grid -->
</div>

<!-- End Page Container -->
</div>
<br>

<!-- Footer -->
<footer class="container">
    <h5></h5>
</footer>


<script>
    // Used to toggle the menu on smaller screens when clicking on the menu button
    function openNav() {
        var x = document.getElementById("navDemo");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }

    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");

    }

    $(function() {

        $('input[name="datefilter" ] ').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear '
            }
        });

        $('input[name="datefilter" ] ').on('apply.daterangepicker ', function(ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY ') + ' - ' + picker.endDate.format('MM/DD/YYYY '));
        });

        $('input[name="datefilter" ] ').on('cancel.daterangepicker ', function(ev, picker) {
            $(this).val(' ');
        });

    });



    function getFileName() {
        var x = document.getElementById('entry_value ')
        document.getElementById('fileName ').innerHTML = x.value.split('\\ ').pop()
    }




</script>

</body>

</html>

