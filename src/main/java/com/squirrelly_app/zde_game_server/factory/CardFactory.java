package com.squirrelly_app.zde_game_server.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squirrelly_app.zde_game_server.model.card.*;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    private static final List<ExploitCard> EXPLOIT_CARDS;

    private static final List<BountyCard> BOUNTY_CARDS;

    public static final ActionCard SYSTEM_CARD_UPGRADE_SYSTEM;
    public static final ActionCard SYSTEM_CARD_EXPLOIT_TARGET;
    public static final ActionCard SYSTEM_CARD_GENERATE_CPU;
    public static final ActionCard SYSTEM_CARD_GENERATE_GPU;
    public static final ActionCard SYSTEM_CARD_GENERATE_NIC;

    public static final ComponentCard COMPONENT_CARD_HDD;
    public static final ComponentCard COMPONENT_CARD_RAM;
    public static final ComponentCard COMPONENT_CARD_CPU;
    public static final ComponentCard COMPONENT_CARD_GPU;
    public static final ComponentCard COMPONENT_CARD_NIC;

    public static final ResourceCard RESOURCE_CARD_CPU_OVERCLOCK;
    public static final ResourceCard RESOURCE_CARD_CPU_ATTACK;
    public static final ResourceCard RESOURCE_CARD_CPU_DEFEND;
    public static final ResourceCard RESOURCE_CARD_GPU_OVERCLOCK;
    public static final ResourceCard RESOURCE_CARD_GPU_ATTACK;
    public static final ResourceCard RESOURCE_CARD_GPU_DEFEND;
    public static final ResourceCard RESOURCE_CARD_NIC_OVERCLOCK;
    public static final ResourceCard RESOURCE_CARD_NIC_ATTACK;
    public static final ResourceCard RESOURCE_CARD_NIC_DEFEND;

    static {

        ObjectMapper mapper = new ObjectMapper();

        try {

            SYSTEM_CARD_UPGRADE_SYSTEM = CardFactory.loadGameDataFile(mapper, ActionCard.class, "system_data/system_cards/system_card_upgrade_system.json");
            SYSTEM_CARD_EXPLOIT_TARGET = CardFactory.loadGameDataFile(mapper, ActionCard.class, "system_data/system_cards/system_card_exploit_target.json");
            SYSTEM_CARD_GENERATE_CPU = CardFactory.loadGameDataFile(mapper, ActionCard.class, "system_data/system_cards/system_card_generate_cpu.json");
            SYSTEM_CARD_GENERATE_GPU = CardFactory.loadGameDataFile(mapper, ActionCard.class, "system_data/system_cards/system_card_generate_gpu.json");
            SYSTEM_CARD_GENERATE_NIC = CardFactory.loadGameDataFile(mapper, ActionCard.class, "system_data/system_cards/system_card_generate_nic.json");

            System.out.println(SYSTEM_CARD_UPGRADE_SYSTEM);
            System.out.println(SYSTEM_CARD_EXPLOIT_TARGET);
            System.out.println(SYSTEM_CARD_GENERATE_CPU);
            System.out.println(SYSTEM_CARD_GENERATE_GPU);
            System.out.println(SYSTEM_CARD_GENERATE_NIC);

            COMPONENT_CARD_HDD = loadGameDataFile(mapper, ComponentCard.class, "system_data/component_cards/component_card_hdd.json");
            COMPONENT_CARD_RAM = loadGameDataFile(mapper, ComponentCard.class, "system_data/component_cards/component_card_ram.json");
            COMPONENT_CARD_CPU = loadGameDataFile(mapper, ComponentCard.class, "system_data/component_cards/component_card_cpu.json");
            COMPONENT_CARD_GPU = loadGameDataFile(mapper, ComponentCard.class, "system_data/component_cards/component_card_gpu.json");
            COMPONENT_CARD_NIC = loadGameDataFile(mapper, ComponentCard.class, "system_data/component_cards/component_card_nic.json");

            System.out.println(COMPONENT_CARD_HDD);
            System.out.println(COMPONENT_CARD_RAM);
            System.out.println(COMPONENT_CARD_CPU);
            System.out.println(COMPONENT_CARD_GPU);
            System.out.println(COMPONENT_CARD_NIC);

            RESOURCE_CARD_CPU_OVERCLOCK = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_cpu_overclock.json");
            RESOURCE_CARD_CPU_ATTACK = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_cpu_attack.json");
            RESOURCE_CARD_CPU_DEFEND = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_cpu_defend.json");
            RESOURCE_CARD_GPU_OVERCLOCK = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_gpu_overclock.json");
            RESOURCE_CARD_GPU_ATTACK = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_gpu_attack.json");
            RESOURCE_CARD_GPU_DEFEND = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_gpu_defend.json");
            RESOURCE_CARD_NIC_OVERCLOCK = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_nic_overclock.json");
            RESOURCE_CARD_NIC_ATTACK = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_nic_attack.json");
            RESOURCE_CARD_NIC_DEFEND = loadGameDataFile(mapper, ResourceCard.class, "system_data/resource_cards/resource_card_nic_defend.json");

            System.out.println(RESOURCE_CARD_CPU_OVERCLOCK);
            System.out.println(RESOURCE_CARD_CPU_ATTACK);
            System.out.println(RESOURCE_CARD_CPU_DEFEND);
            System.out.println(RESOURCE_CARD_GPU_OVERCLOCK);
            System.out.println(RESOURCE_CARD_GPU_ATTACK);
            System.out.println(RESOURCE_CARD_GPU_DEFEND);
            System.out.println(RESOURCE_CARD_NIC_OVERCLOCK);
            System.out.println(RESOURCE_CARD_NIC_ATTACK);
            System.out.println(RESOURCE_CARD_NIC_DEFEND);

            EXPLOIT_CARDS = loadGameDataDir(mapper, ExploitCard.class, "system_data/exploit_cards/exploit_card_%d.json", 9);

            for (ExploitCard exploitCard : EXPLOIT_CARDS) {
                System.out.println(exploitCard);
            }

            BOUNTY_CARDS = loadGameDataDir(mapper, BountyCard.class, "system_data/bounty_cards/bounty_card_%d.json", 20);

            for (BountyCard bountyCard : BOUNTY_CARDS) {
                System.out.println(bountyCard);
            }

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        }

    }

    private static <T> T loadGameDataFile(ObjectMapper mapper, Class<T> clazz, String filepath) throws FileNotFoundException {

        try (InputStream inputStream = new ClassPathResource(filepath).getInputStream()) {

            return mapper.readValue(inputStream, clazz);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    private static <T> List<T> loadGameDataDir(ObjectMapper mapper, Class<T> clazz, String filepathTemplate, Integer count) throws FileNotFoundException {

        List<T> objects = new ArrayList<>();

        try {

            for (int index = 1; index <= count; index++) {
                objects.add( loadGameDataFile(mapper, clazz, String.format(filepathTemplate, index)) );
            }

            return objects;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public static List<ActionCard> getSystemActionCards() {

        List<ActionCard> actionCards = new ArrayList<>();

        actionCards.add(SYSTEM_CARD_UPGRADE_SYSTEM);
        actionCards.add(SYSTEM_CARD_EXPLOIT_TARGET);
        actionCards.add(SYSTEM_CARD_GENERATE_CPU);
        actionCards.add(SYSTEM_CARD_GENERATE_GPU);
        actionCards.add(SYSTEM_CARD_GENERATE_NIC);

        return Collections.unmodifiableList(actionCards);

    }

    public static List<ComponentCard> getComponentCards() {

        List<ComponentCard> componentCards = new ArrayList<>();

        componentCards.add(COMPONENT_CARD_HDD);
        componentCards.add(COMPONENT_CARD_RAM);
        componentCards.add(COMPONENT_CARD_CPU);
        componentCards.add(COMPONENT_CARD_GPU);
        componentCards.add(COMPONENT_CARD_NIC);

        return Collections.unmodifiableList(componentCards);

    }

    public static List<ResourceCard> getResourceCards() {

        List<ResourceCard> resourceCards = new ArrayList<>();

        resourceCards.add(RESOURCE_CARD_CPU_OVERCLOCK);
        resourceCards.add(RESOURCE_CARD_CPU_ATTACK);
        resourceCards.add(RESOURCE_CARD_CPU_DEFEND);
        resourceCards.add(RESOURCE_CARD_GPU_OVERCLOCK);
        resourceCards.add(RESOURCE_CARD_GPU_ATTACK);
        resourceCards.add(RESOURCE_CARD_GPU_DEFEND);
        resourceCards.add(RESOURCE_CARD_NIC_OVERCLOCK);
        resourceCards.add(RESOURCE_CARD_NIC_ATTACK);
        resourceCards.add(RESOURCE_CARD_NIC_DEFEND);

        return Collections.unmodifiableList(resourceCards);

    }

    public static List<ExploitCard> getExploitCards() {

        return Collections.unmodifiableList(EXPLOIT_CARDS);

    }

    public static List<BountyCard> getBountyCards() {

        return Collections.unmodifiableList(BOUNTY_CARDS);

    }

}
