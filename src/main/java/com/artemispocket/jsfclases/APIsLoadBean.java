package com.artemispocket.jsfclases;

import com.artemispocket.ApiConn.SPOJAPI;
import com.artemispocket.ApiConn.CodeChefAPI;
import com.artemispocket.ApiConn.CodeforcesAPI;
import com.artemispocket.ApiConn.CodeforcesAPI.SubmissionsResult;
import com.artemispocket.ApiConn.UVaAPI;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author roca12
 */
@Named(value = "aPIsLoadBean")
@ConversationScoped
public class APIsLoadBean implements Serializable {

    private UVaAPI.EstadisticasCompletas aux;
    private String name;
    private String uname;
    private List<Integer> problemsTried = new ArrayList<>();
    private List<Integer> problemsCorrects = new ArrayList<>();
    private Integer submissions = 0;
    private Integer accepted = 0;
    private Integer wronganswer = 0;
    private Integer timelimt = 0;
    private Integer runtimeerror = 0;
    private Integer presentationerror = 0;
    private Integer submissionerror = 0;
    private Integer cantbejudged = 0;
    private Integer inqueue = 0;
    private Integer compileerror = 0;
    private Integer restrictedfunction = 0;
    private Integer outputlimit = 0;
    private Integer memorylimit = 0;
    private Integer ANSIC = 0;
    private Integer JAVA = 0;
    private Integer CPP = 0;
    private Integer PASCAL = 0;
    private Integer CPP11 = 0;
    private Integer numberTried = 0;
    private Integer numberDone = 0;
    private DonutChartModel donutModel;
    private DonutChartModel donutModel2;
    private BarChartModel barModel;
    private ArrayList<Problem> problemslist = new ArrayList<>();
    private CodeChefAPI.Codechef codechef;
    private SPOJAPI.SpojUserStats spojUserStats;
    private CodeforcesAPI.Pair p;
    private CodeforcesAPI.UserInfoResult uir;
    private List<CodeforcesAPI.SubmissionsResult> result;
    private List<CFSubs> reslist = new ArrayList<>();
    private Integer acceptedcf = 0;

    /**
     * Creates a new instance of APIsLoadBean
     */
    public APIsLoadBean() {
    }

    public void onload() {
        try {
            aux = UVaAPI.getJSONUVa(UVaAPI.getidUVa("diegoroca17") + "");
        } catch (IOException ex) {
            Logger.getLogger(APIsLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        accepted = aux.accepted;
        name = aux.uname;
        problemsTried = aux.problemsTried;
        problemsCorrects = aux.problemsCorrects;
        submissions = aux.submissions;
        accepted = aux.accepted;
        wronganswer = aux.wronganswer;
        timelimt = aux.timelimt;
        runtimeerror = aux.runtimeerror;
        presentationerror = aux.presentationerror;
        submissionerror = aux.submissionerror;
        cantbejudged = aux.cantbejudged;
        inqueue = aux.inqueue;
        compileerror = aux.compileerror;
        restrictedfunction = aux.restrictedfunction;
        outputlimit = aux.outputlimit;
        memorylimit = aux.memorylimit;
        ANSIC = aux.ANSIC;
        JAVA = aux.JAVA;
        CPP = aux.CPP;
        PASCAL = aux.PASCAL;
        CPP11 = aux.CPP11;
        numberDone = problemsCorrects.size();
        numberTried = problemsTried.size();
        loadChartVeredictos();
        loadChartLenguajes();
        //https://onlinejudge.org/external/101/10109.pdf
        for (int i = 0; i < problemsCorrects.size(); i++) {
            try {
                loadProblemsCorrect(problemsCorrects.get(i));
            } catch (IOException ex) {
                Logger.getLogger(APIsLoadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        CodeChefAPI c = new CodeChefAPI();
        try {
            codechef = c.getJSONCodechef("bryanttv");
        } catch (IOException ex) {
            Logger.getLogger(APIsLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        SPOJAPI s = new SPOJAPI();
        //https://www.spoj.com/problems/TEST/
        try {
            spojUserStats = s.getJSONSPOJ("edanv");
        } catch (IOException ex) {
            Logger.getLogger(APIsLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        CodeforcesAPI cf = new CodeforcesAPI();
        try {
            p = cf.getJSONCodeforces("DmitriyH");
            uir = p.getI().result.get(0);
            result = p.getS().result;

            HashSet<String> set = new HashSet<>();
            for (SubmissionsResult i : result) {
                CFSubs auxcfsubs = new CFSubs();
                if (i.verdict.equals("OK") && set.add(i.problem.name)) {
                    auxcfsubs.name = i.problem.name;

                    for (String s2 : i.problem.tags) {
                        auxcfsubs.tags.add(s2);
                    }
                    auxcfsubs.languaje = i.programmingLanguage;
                    auxcfsubs.index=i.problem.index;
                    auxcfsubs.id=i.problem.contestId+"";
                    acceptedcf++;
                }
                reslist.add(auxcfsubs);
            }
        } catch (IOException ex) {
            Logger.getLogger(APIsLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadChartLenguajesCf();
    }

    public class CFSubs {

        String name;
        ArrayList<String> tags = new ArrayList<>();
        String languaje;
        String index;
        String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getTags() {
            return tags;
        }

        public void setTags(ArrayList<String> tags) {
            this.tags = tags;
        }

        public String getLanguaje() {
            return languaje;
        }

        public void setLanguaje(String languaje) {
            this.languaje = languaje;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        
        
        
        
    }

    public void loadProblemsCorrect(int problemid) throws MalformedURLException, IOException {
        String sURL = "https://uhunt.onlinejudge.org/api/p/id/" + problemid;
        // Connect to the URL using java's native library
        URL url;
        url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        Problem pro = new Gson().fromJson(rootobj, Problem.class);
        switch ((pro.num + "").length()) {
            case 5: {
                String a = (pro.num + "").substring(0, 3);
                String s = "https://onlinejudge.org/external/" + a + "/" + pro.num + ".pdf";
                pro.URL = s;
                break;
            }
            case 4: {
                String a = (pro.num + "").substring(0, 2);
                String s = "https://onlinejudge.org/external/" + a + "/" + pro.num + ".pdf";
                pro.URL = s;
                break;
            }
            case 3: {
                String a = (pro.num + "").substring(0, 1);
                String s = "https://onlinejudge.org/external/" + a + "/" + pro.num + ".pdf";
                pro.URL = s;
                break;
            }
            default:
                break;
        }
        problemslist.add(pro);

    }

    public class Problem {

        public int pid;
        public int num;
        public String title;
        public int dacu;
        public int mrun;
        public int mmem;
        public int nover;
        public int sube;
        public int noj;
        public int inq;
        public int ce;
        public int rf;
        public int re;
        public int ole;
        public int tle;
        public int mle;
        public int wa;
        public int pe;
        public int ac;
        public int rtl;
        public int status;
        public int rej;
        public String URL;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDacu() {
            return dacu;
        }

        public void setDacu(int dacu) {
            this.dacu = dacu;
        }

        public int getMrun() {
            return mrun;
        }

        public void setMrun(int mrun) {
            this.mrun = mrun;
        }

        public int getMmem() {
            return mmem;
        }

        public void setMmem(int mmem) {
            this.mmem = mmem;
        }

        public int getNover() {
            return nover;
        }

        public void setNover(int nover) {
            this.nover = nover;
        }

        public int getSube() {
            return sube;
        }

        public void setSube(int sube) {
            this.sube = sube;
        }

        public int getNoj() {
            return noj;
        }

        public void setNoj(int noj) {
            this.noj = noj;
        }

        public int getInq() {
            return inq;
        }

        public void setInq(int inq) {
            this.inq = inq;
        }

        public int getCe() {
            return ce;
        }

        public void setCe(int ce) {
            this.ce = ce;
        }

        public int getRf() {
            return rf;
        }

        public void setRf(int rf) {
            this.rf = rf;
        }

        public int getRe() {
            return re;
        }

        public void setRe(int re) {
            this.re = re;
        }

        public int getOle() {
            return ole;
        }

        public void setOle(int ole) {
            this.ole = ole;
        }

        public int getTle() {
            return tle;
        }

        public void setTle(int tle) {
            this.tle = tle;
        }

        public int getMle() {
            return mle;
        }

        public void setMle(int mle) {
            this.mle = mle;
        }

        public int getWa() {
            return wa;
        }

        public void setWa(int wa) {
            this.wa = wa;
        }

        public int getPe() {
            return pe;
        }

        public void setPe(int pe) {
            this.pe = pe;
        }

        public int getAc() {
            return ac;
        }

        public void setAc(int ac) {
            this.ac = ac;
        }

        public int getRtl() {
            return rtl;
        }

        public void setRtl(int rtl) {
            this.rtl = rtl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRej() {
            return rej;
        }

        public void setRej(int rej) {
            this.rej = rej;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

    }

    public void loadChartVeredictos() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();

        List< Number> values = new ArrayList<>();
        values.add(accepted);
        values.add(presentationerror);
        values.add(wronganswer);
        values.add(memorylimit);
        values.add(timelimt);
        values.add(outputlimit);
        values.add(runtimeerror);
        values.add(compileerror);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(156, 255, 51)");
        bgColors.add("rgb(241, 185, 44)");
        bgColors.add("rgb(255, 0, 0)");
        bgColors.add("rgb(55, 177, 247)");
        bgColors.add("rgb(177, 179, 31)");
        bgColors.add("rgb(159, 31, 179 )");
        bgColors.add("rgb(128, 124, 128)");
        bgColors.add("rgb(6, 102, 136)");

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();

        labels.add("Accepted");
        labels.add("Presentation Error");
        labels.add("Wrong answer");
        labels.add("Memory limit");
        labels.add("Time limit");
        labels.add("Output limit");
        labels.add("Runtime error");
        labels.add("Compile error");

        data.setLabels(labels);
        donutModel.setData(data);
    }

    public void loadChartLenguajes() {
        donutModel2 = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();

        List< Number> values = new ArrayList<>();
        values.add(ANSIC);
        values.add(CPP);
        values.add(CPP11);
        values.add(PASCAL);
        values.add(JAVA);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 8, 0)");
        bgColors.add("rgb(238, 217, 11)");
        bgColors.add("rgb(10, 248, 13)");
        bgColors.add("rgb(248, 161, 10)");
        bgColors.add("rgb(10, 28, 248)");

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();

        labels.add("ANSI C");
        labels.add("C++");
        labels.add("C++ 11");
        labels.add("PASCAL");
        labels.add("JAVA");

        data.setLabels(labels);
        donutModel2.setData(data);
    }

    public void loadChartLenguajesCf() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet dataSet = new BarChartDataSet();

        List< Number> values = new ArrayList<>();
        ArrayList<String> set = new ArrayList<>();
        ArrayList<Integer> value = new ArrayList<>();
        for (CFSubs v : reslist) {
//            if (set.size()==4) {
//                break;
//            }
            if (v.languaje == null) {
                continue;
            }
            if (!set.contains(v.languaje)) {
                set.add(v.languaje);
                value.add(0);
            } else {
                value.set(set.indexOf(v.languaje), value.get(set.indexOf(v.languaje)) + 1);
            }
        }
        for (Integer i : value) {
            values.add(i);
            System.out.println(i);
        }
        dataSet.setData(values);
        ArrayList<String> colores = new ArrayList<>();
        colores.add("rgb(255, 8, 0)");
        colores.add("rgb(0, 255, 3)");
        colores.add("rgb(255, 166, 0 )");
        colores.add("rgb(255, 247, 0)");
        colores.add("rgb(0, 255, 212)");
        colores.add("rgb(0, 97, 255)");
        colores.add("rgb(103, 18, 174)");
        colores.add("rgb(174, 18, 87)");
        colores.add("rgb(0, 0, 0)");
        colores.add("rgb(250, 236, 209)");

        List<String> bgColors = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            bgColors.add(colores.get(i));

        }

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < set.size(); i++) {
            labels.add(set.get(i));
            System.out.println(set.get(i));
        }

        data.setLabels(labels);
        barModel.setData(data);
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);


        Legend legend = new Legend();
        legend.setDisplay(false);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }

//    public void loadChartVisitasMesAnio() {
//        barModel = new BarChartModel();
//        ChartData data = new ChartData();
//        BarChartDataSet barDataSet = new BarChartDataSet();
//        barDataSet.setLabel("Accesos correctos");
//        List<Number> values2 = new ArrayList<>();
//        List<Visitaspormesanio> list = vistasMesAnioService.getAll();
//        Visitaspormesanio anioactual = new Visitaspormesanio();
//        for (Visitaspormesanio vpm : list) {
//            if (vpm.getAnio() == Calendar.getInstance().get(Calendar.YEAR)) {
//                anioactual = vpm;
//            }
//        }
//        values2.add(anioactual.getEnero());
//        values2.add(anioactual.getFebrero());
//        values2.add(anioactual.getMarzo());
//        values2.add(anioactual.getAbril());
//        values2.add(anioactual.getMayo());
//        values2.add(anioactual.getJunio());
//        values2.add(anioactual.getJulio());
//        values2.add(anioactual.getAgosto());
//        values2.add(anioactual.getSeptiembre());
//        values2.add(anioactual.getOctubre());
//        values2.add(anioactual.getNoviembre());
//        values2.add(anioactual.getDiciembre());
//
//        barDataSet.setData(values2);
//
//        List<String> bgColor2 = new ArrayList<>();
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//        bgColor2.add("rgba(16, 128, 33, 0.2)");
//
//        barDataSet.setBackgroundColor(bgColor2);
//
//        List<String> borderColor2 = new ArrayList<>();
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        borderColor2.add("rgb(54, 162, 235)");
//        barDataSet.setBorderColor(borderColor2);
//        barDataSet.setBorderWidth(2);
//
//        data.addChartDataSet(barDataSet);
//
//        List<String> labels2 = new ArrayList<>();
//        labels2.add("Enero");
//        labels2.add("Febrero");
//        labels2.add("Marzo");
//        labels2.add("Abril");
//        labels2.add("Mayo");
//        labels2.add("Junio");
//        labels2.add("Julio");
//        labels2.add("Agosto");
//        labels2.add("Septiembre");
//        labels2.add("Octubre");
//        labels2.add("Noviembre");
//        labels2.add("Diciembre");
//        data.setLabels(labels2);
//
//        //Data
//        barModel.setData(data);
//    }
    public UVaAPI.EstadisticasCompletas getAux() {
        return aux;
    }

    public void setAux(UVaAPI.EstadisticasCompletas aux) {
        this.aux = aux;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public List<Integer> getProblemsTried() {
        return problemsTried;
    }

    public void setProblemsTried(List<Integer> problemsTried) {
        this.problemsTried = problemsTried;
    }

    public List<Integer> getProblemsCorrects() {
        return problemsCorrects;
    }

    public void setProblemsCorrects(List<Integer> problemsCorrects) {
        this.problemsCorrects = problemsCorrects;
    }

    public Integer getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Integer submissions) {
        this.submissions = submissions;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public Integer getWronganswer() {
        return wronganswer;
    }

    public void setWronganswer(Integer wronganswer) {
        this.wronganswer = wronganswer;
    }

    public Integer getTimelimt() {
        return timelimt;
    }

    public void setTimelimt(Integer timelimt) {
        this.timelimt = timelimt;
    }

    public Integer getRuntimeerror() {
        return runtimeerror;
    }

    public void setRuntimeerror(Integer runtimeerror) {
        this.runtimeerror = runtimeerror;
    }

    public Integer getPresentationerror() {
        return presentationerror;
    }

    public void setPresentationerror(Integer presentationerror) {
        this.presentationerror = presentationerror;
    }

    public Integer getSubmissionerror() {
        return submissionerror;
    }

    public void setSubmissionerror(Integer submissionerror) {
        this.submissionerror = submissionerror;
    }

    public Integer getCantbejudged() {
        return cantbejudged;
    }

    public void setCantbejudged(Integer cantbejudged) {
        this.cantbejudged = cantbejudged;
    }

    public Integer getInqueue() {
        return inqueue;
    }

    public void setInqueue(Integer inqueue) {
        this.inqueue = inqueue;
    }

    public Integer getCompileerror() {
        return compileerror;
    }

    public void setCompileerror(Integer compileerror) {
        this.compileerror = compileerror;
    }

    public Integer getRestrictedfunction() {
        return restrictedfunction;
    }

    public void setRestrictedfunction(Integer restrictedfunction) {
        this.restrictedfunction = restrictedfunction;
    }

    public Integer getOutputlimit() {
        return outputlimit;
    }

    public void setOutputlimit(Integer outputlimit) {
        this.outputlimit = outputlimit;
    }

    public Integer getMemorylimit() {
        return memorylimit;
    }

    public void setMemorylimit(Integer memorylimit) {
        this.memorylimit = memorylimit;
    }

    public Integer getANSIC() {
        return ANSIC;
    }

    public void setANSIC(Integer ANSIC) {
        this.ANSIC = ANSIC;
    }

    public Integer getJAVA() {
        return JAVA;
    }

    public void setJAVA(Integer JAVA) {
        this.JAVA = JAVA;
    }

    public Integer getCPP() {
        return CPP;
    }

    public void setCPP(Integer CPP) {
        this.CPP = CPP;
    }

    public Integer getPASCAL() {
        return PASCAL;
    }

    public void setPASCAL(Integer PASCAL) {
        this.PASCAL = PASCAL;
    }

    public Integer getCPP11() {
        return CPP11;
    }

    public void setCPP11(Integer CPP11) {
        this.CPP11 = CPP11;
    }

    public Integer getNumberTried() {
        return numberTried;
    }

    public void setNumberTried(Integer numberTried) {
        this.numberTried = numberTried;
    }

    public Integer getNumberDone() {
        return numberDone;
    }

    public void setNumberDone(Integer numberDone) {
        this.numberDone = numberDone;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }

    public void setDonutModel2(DonutChartModel donutModel2) {
        this.donutModel2 = donutModel2;
    }

    public ArrayList<Problem> getProblemslist() {
        return problemslist;
    }

    public void setProblemslist(ArrayList<Problem> problemslist) {
        this.problemslist = problemslist;
    }

    public CodeChefAPI.Codechef getCodechef() {
        return codechef;
    }

    public void setCodechef(CodeChefAPI.Codechef codechef) {
        this.codechef = codechef;
    }

    public SPOJAPI.SpojUserStats getSpojUserStats() {
        return spojUserStats;
    }

    public void setSpojUserStats(SPOJAPI.SpojUserStats spojUserStats) {
        this.spojUserStats = spojUserStats;
    }

    public CodeforcesAPI.Pair getP() {
        return p;
    }

    public void setP(CodeforcesAPI.Pair p) {
        this.p = p;
    }

    public CodeforcesAPI.UserInfoResult getUir() {
        return uir;
    }

    public void setUir(CodeforcesAPI.UserInfoResult uir) {
        this.uir = uir;
    }

    public List<SubmissionsResult> getResult() {
        return result;
    }

    public void setResult(List<SubmissionsResult> result) {
        this.result = result;
    }

    public Integer getAcceptedcf() {
        return acceptedcf;
    }

    public void setAcceptedcf(Integer acceptedcf) {
        this.acceptedcf = acceptedcf;
    }

    public List<CFSubs> getReslist() {
        return reslist;
    }

    public void setReslist(List<CFSubs> reslist) {
        this.reslist = reslist;
    }

}
