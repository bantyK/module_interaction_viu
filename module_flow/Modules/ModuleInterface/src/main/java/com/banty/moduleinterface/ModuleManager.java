package com.banty.moduleinterface;

import java.util.HashMap;

/**
 * Created by Banty on 26/04/19.
 */
public class ModuleManager {

    private static final String TAG = ModuleManager.class.getSimpleName();

    /**
     * Singleton.
     */
    private static ModuleManager instance = new ModuleManager();

    /**
     * Map of modules.
     */
    private HashMap<String, Module> mModules = new HashMap<>();

    /**
     * Singleton constructor.
     */
    private ModuleManager() {

    }

    /**
     * Get singleton instance.
     *
     * @return Singleton instance.
     */
    public static ModuleManager getInstance() {

        return instance;
    }

    /**
     * Set module by name.
     *
     * @param name   Module name.
     * @param module Module instance.
     */
    public void setModule(String name, Module module) {

        mModules.put(name, module);
    }

    /**
     * Get module by name.
     *
     * @param name Module name.
     * @return Module instance.
     */
    public Module getModule(String name) {

        return mModules.get(name);
    }

}
