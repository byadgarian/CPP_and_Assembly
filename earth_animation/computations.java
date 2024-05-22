//********** PROGRAM INFORMATION **********************************************************************
//  Program Name: Earth
//  Student Name: Brian Y
//  Student E-mail: ***

//********** MAIN CODE AREA **********************************************************************
public class computations       //mathematical computations class
{
    private double Δt;

    public double calcΔt(double orbitRadious, double earthSpeed)
    {
        Δt = earthSpeed/orbitRadious;
        return Δt;
    }
}   //end of the computations class