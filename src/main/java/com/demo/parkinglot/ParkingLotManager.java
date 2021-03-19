package com.demo.parkinglot;

import com.demo.parkinglot.exceptions.ParkingLotException;
import com.demo.parkinglot.model.ParkingLot;
import com.demo.parkinglot.model.ParkingSpace;
import com.demo.parkinglot.services.InstructionExecutorService;
import com.demo.parkinglot.util.InstructionParsingUtil;
import com.google.common.base.Strings;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.demo.parkinglot.util.InstructionParsingUtil.CREATE_PARKING_LOT;
import static com.demo.parkinglot.util.InstructionParsingUtil.CREATE_PARKING_LOT_REGEX;

/*
* This class is responsible for reading the input text file, parse it and then call the
* respective services to perform the instructions.
* */
public class ParkingLotManager {

    private final InstructionExecutorService instructionExecutorService;

    public ParkingLotManager(InstructionExecutorService instructionExecutorService) {
        this.instructionExecutorService = instructionExecutorService;
    }

    public void manageParkingLot(String filePathFromArgs) throws IOException {
        BufferedReader br = null;
        InputStream propInputStream = null;
        try {
            propInputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();
            prop.load(propInputStream);

            br = new BufferedReader(new InputStreamReader(getFilePath(filePathFromArgs, prop)));

            //reading first line which should always be createParkingLot
            String firstLine = br.readLine();
            ParkingLot parkingLot = createParkingLot(firstLine);

            String line;
            List<Pair<String, ParkingSpace>> instructionAndParkingSpacePairs = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // process the line. ignore if there is another createParkingLot instruction
                if (!line.startsWith(CREATE_PARKING_LOT)) {
                    instructionAndParkingSpacePairs
                            .add(instructionExecutorService.parseInstruction(line));
                }
            }
            //executing each instruction
            instructionAndParkingSpacePairs.forEach(pair ->
                    instructionExecutorService.executeInstruction(parkingLot,
                            pair.getValue(), pair.getKey()));

        }
        catch (IOException | ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(propInputStream != null) {
                propInputStream.close();
            }
            if(br != null) {
                br.close();
            }
        }

    }

    private ParkingLot createParkingLot(String line) throws ParkingLotException {
        if (!InstructionParsingUtil.containsParsingError(CREATE_PARKING_LOT_REGEX, line)) {
            int numberOfSlots = Integer.parseInt(line.split(" ")[1]);
            ParkingLot parkingLot = ParkingLot.getInstance(numberOfSlots);
            System.out.println(MessageFormat.format("Created parking of {0} slots", numberOfSlots));
            return parkingLot;
        } else {
            throw new ParkingLotException("ERROR: Parking lot could not be created. Parsing error in line : " + line);
        }
    }

    private InputStream getFilePath(String filePathFromArgs, Properties properties) throws FileNotFoundException {
        String filePath;
        if(!Strings.isNullOrEmpty(filePathFromArgs)) {
            filePath = filePathFromArgs;
        }
        //if properties file does not contain the filePath, then use input.txt file present in the project dir
        else if(Strings.isNullOrEmpty(properties.getProperty("input.file.name"))) {
            return getClass().getClassLoader().getResourceAsStream("input.txt");
        }
        else {
            filePath = properties.getProperty("input.file.name");
        }
        return new FileInputStream(filePath);
    }
}
