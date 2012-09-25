<%
    response.setHeader("Pragma","No-cache");        //HTTP     1.1
    response.setHeader("Cache-Control","no-cache");//HTTP     1.0
    response.setHeader("Expires","0");               //防止被proxy
%>
<link rel="stylesheet" type="text/css" href="${themesPath}/ext/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="${themesPath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${themesPath}/mocha/css/Content.css" />
	<link rel="stylesheet" type="text/css" href="${themesPath}/mocha/css/Core.css" />
	<link rel="stylesheet" type="text/css" href="${themesPath}/mocha/css/Layout.css" />
	<link rel="stylesheet" type="text/css" href="${themesPath}/mocha/css/Dock.css" />
	<link rel="stylesheet" type="text/css" href="${themesPath}/mocha/css/Window.css" />
	<link rel="stylesheet" type="text/css" href="${themesPath}/mocha/css/Tabs.css" />

    <script type="text/javascript" src="${scriptsPath}/mocha/excanvas_r43.js"></script>

    <script type="text/javascript" src="${scriptsPath}/mootools/mootools-core.js"></script>
	<script type="text/javascript" src="${scriptsPath}/mootools/mootools-more.js"></script>

    <%--<script type="text/javascript" src="${scriptsPath}/mocha/source/Core/Core.js"></script>--%>
	<%--<script type="text/javascript" src="${scriptsPath}/mocha/source/Layout/Layout.js"></script>--%>
	<%--<script type="text/javascript" src="${scriptsPath}/mocha/source/Layout/Dock.js"></script>--%>
	<%--<script type="text/javascript" src="${scriptsPath}/mocha/source/Window/Window.js"></script>--%>
	<%--<script type="text/javascript" src="${scriptsPath}/mocha/source/Window/Modal.js"></script>--%>
	<%--<script type="text/javascript" src="${scriptsPath}/mocha/source/Components/Tabs.js"></script>--%>

    <script type="text/javascript">

        function enter(modulename, url,w,h) {
            if(parent.MUI != null){
                new parent.MUI.Window({
                    id: modulename,
                    title: modulename,
                    restrict: false,
                    loadMethod: 'iframe',
                    contentURL: url,
            //                container: 'pageWrapper',
                    width: w,
                    height: h,
                    x: 0,
                    y: 0
                });
            }
            else if(parent.parent.MUI != null){
                new parent.parent.MUI.Window({
                    id: modulename,
                    title: modulename,
                    restrict: false,
                    loadMethod: 'iframe',
                    contentURL: url,
            //                container: 'pageWrapper',
                    width: w,
                    height: h,
                    x: 0,
                    y: 0
                });
            }
            else if(parent.parent.parent.MUI != null){
                new parent.parent.parent.MUI.Window({
                    id: modulename,
                    title: modulename,
                    restrict: false,
                    loadMethod: 'iframe',
                    contentURL: url,
            //                container: 'pageWrapper',
                    width: w,
                    height: h,
                    x: 0,
                    y: 0
                });
            }else if(parent.parent.parent.parent.MUI != null){
                new parent.parent.parent.parent.MUI.Window({
                    id: modulename,
                    title: modulename,
                    restrict: false,
                    loadMethod: 'iframe',
                    contentURL: url,
            //                container: 'pageWrapper',
                    width: w,
                    height: h,
                    x: 0,
                    y: 0
                });
            }
            else {
                window.open(url);
            }
        }

        function closewindow(id){
            if(parent.$(id) != null){
                parent.MUI.closeWindow(parent.$(id));
            }else if(parent.parent.$(id) != null){
                parent.parent.MUI.closeWindow(parent.parent.$(id));
            }else if(parent.parent.parent.$(id) != null){
                parent.parent.parent.MUI.closeWindow(parent.parent.parent.$(id));
            }else if(parent.parent.parent.parent.$(id) != null){
                parent.parent.parent.parent.MUI.closeWindow(parent.parent.parent.parent.$(id));
            }
        }
        </script>
